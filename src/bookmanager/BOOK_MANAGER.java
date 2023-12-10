package bookmanager;


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


    //别忘了在子类调用setTableName
    @Override
    public boolean createTable() throws ConnectException, SQLException {
        //连接是否丢失
        if (connection == null) {
            throw new ConnectException();
        }
        String createTableStatement = null;

        //选择要建表的语句
        switch (getTableId()) {
            case 1 -> createTableStatement = TABLE_CREATE_BOOKS_STATEMENT;
            case 2 -> createTableStatement = TABLE_CREATE_BOOK_COPIES_STATEMENT;
            case 3 -> createTableStatement = TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT;
        }
        statement = connection.prepareStatement(createTableStatement);

        //建表是否成功返回
        return statement.execute();
    }

    public abstract ArrayList<Object>  insert(ArrayList<?> insertArrayOutGet) throws SQLException ;

    @Override
    public int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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

    @Override
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
    BOOK_MANAGER() throws SQLException, ConnectException {    //connect MySQL server
        connection = getConnection();
        createTable();
    }

    public static final String TABLE_CREATE_BOOKS_STATEMENT = """
            CREATE TABLE if NOT EXISTS Books(
             book_id INT AUTO_INCREMENT PRIMARY KEY,
             title VARCHAR(50) NOT NULL,
             isbn VARCHAR(13) UNIQUE,
             author VARCHAR(50) NOT NULL,
             publisher VARCHAR(50) NOT NULL,
             receipt_date DATE NOT NULL,
             genre VARCHAR(50) NOT NULL,
             book_quantity_visible INT NOT NULL,
             book_quantity_hidden INT NOT NULL
            );""";
    public static final String TABLE_CREATE_BOOK_COPIES_STATEMENT = """
                CREATE TABLE IF NOT EXISTS BookCopies (
                copy_id INT AUTO_INCREMENT PRIMARY KEY,
                book_id INT,
                acquisition_date DATE,
                on_shelf_status BOOLEAN DEFAULT 'TRUE',
                book_location VARCHAR(50),
                is_visible BOOLEAN DEFAULT 'TRUE',
                FOREIGN KEY (book_id) REFERENCES Books(book_id)
            );""";

    public static final String TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT = """
                CREATE TABLE IF NOT EXISTS LibraryCollectionRoom (
                copy_id INT PRIMARY KEY,
                room VARCHAR(10) NOT NULL,
                shelf_id CHAR(2) NOT NULL,
                compartment_id CHAR(3),
                inner_number CHAR(5),
                FOREIGN KEY (copy_id) REFERENCES BookCopies(copy_id)
            );""";

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
