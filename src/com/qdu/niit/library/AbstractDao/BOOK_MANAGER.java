package com.qdu.niit.library.AbstractDao;


import com.qdu.niit.library.GenericDao.MANAGER;
import com.qdu.niit.library.dao.imple.BaseSQLDaoImpl;
import com.qdu.niit.library.utils.SqlConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class BOOK_MANAGER extends BaseSQLDaoImpl implements MANAGER {
    Connection connection = null;
    PreparedStatement statement = null;
    String tableName = null;  //需要初始化
    String tableId = null;

    abstract protected String getCreateTableStatement();
    //别忘了在子类调用setTableName
    @Override
    public void createTable() throws ConnectException, SQLException {
        //连接是否丢失
        if (connection == null) {
            throw new ConnectException();
        }
        String createTableStatement = null;

        //选择要建表的语句
        createTableStatement = getCreateTableStatement();
        statement = connection.prepareStatement(createTableStatement);

        //建表是否成功返回
        statement.execute();
        connection.close();
    }


    /*如果这样的话public abstract String getDeleteByBookIdStatement();有些不需要book_id删除的类也需要写，所以用反射
        就不会强制要求必须有此函数*/

    public int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int theNumberOfEffectedLines = 0;

        Class<?> clazz = this.getClass();

        Method getObjectStatement;
        getObjectStatement = clazz.getDeclaredMethod("getDeleteByBookIdStatement");
        getObjectStatement.setAccessible(true);   //获得对应的调用对象的函数


        if (theKeyWantDelete != null) {
            for (Integer book_id : theKeyWantDelete) {
                executeUpdateAndGetGeneratedKeys(
                        (String) getObjectStatement.invoke(null),
                        book_id
                );
                theNumberOfEffectedLines++;
            }
        }
        return theNumberOfEffectedLines;
    }

    public int deleteByCopyID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int theNumberOfEffectedLines = 0;
        String objClass;


        Class<?> clazz = this.getClass();
        String name = clazz.getName();
        Pattern pattern = Pattern.compile("(.+?)Dao");  //因为调用函数的位置在Books等类中，通过反射获得前面的内容
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            objClass = matcher.group(1);
        } else {
            throw new SQLException();
        }
        clazz = Class.forName(objClass);
        Method getObjectStatement;
        getObjectStatement = clazz.getDeclaredMethod("getDeleteByCopyIdStatement");
        getObjectStatement.setAccessible(true);   //获得对应的调用对象的函数


        if (theKeyWantDelete != null) {
            for (Integer copy_id : theKeyWantDelete) {
                executeUpdateAndGetGeneratedKeys(
                        (String) getObjectStatement.invoke(null),
                        copy_id
                );
                theNumberOfEffectedLines++;
            }
        }
        return theNumberOfEffectedLines;

    }
    public BOOK_MANAGER() throws SQLException, ConnectException {    //connect MySQL server
        connection = getConnection();
        createTable();
    }
    public BOOK_MANAGER(int everything){
        ;
    }





    //获得 Connection
    @Override
    public Connection getConnection() {
        Connection conn;
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/qdu?serverTimezone=GMT%2B8", "root", "root");//配置数据库
        try {
            conn = SqlConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}