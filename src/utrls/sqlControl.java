package utrls;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class sqlControl {
    private ArrayList<ArrayList<String>> back;
    private static Connection conn;
    private worktoolSQL wl;//
    static Driver driver;
    static Properties info;
    static String url;
    private static String make1 = "CREATE TABLE IF NOT EXISTS Personal_Information(uid INT NOT NULL,uname VARCHAR(20) NOT NULL,uage INT,ugender INT,uphone VARCHAR(20),uemail VARCHAR(50));";
    private static String make2 = "CREATE TABLE  IF NOT EXISTS Account(uid INT NOT NULL,uname VARCHAR(30) NOT NULL,upawd VARCHAR(30) NOT NULL);";
    private static String make3 = "CREATE TABLE  IF NOT EXISTS Book_Information(bid INT NOT NULL,bname VARCHAR(50) NOT NULL,bauthor VARCHAR(50),bcategory VARCHAR(20),bamount INT,bposition VARCHAR(20));";
    private static String make4 = "CREATE TABLE  IF NOT EXISTS Borrowing_Information(uid INT NOT NULL,bid INT NOT NULL,start_time DATETIME NOT NULL,end_time DATETIME NOT NULL);";
    private static String make5 = "CREATE TABLE  IF NOT EXISTS Room_LIst(rid INT NOT NULL,rname INT NOT NULL,rfloor INT NOT NULL);";
    private static PreparedStatement st1;
    public sqlControl() throws SQLException {
        wl = new worktoolSQL();
        conn = null;
        back = new ArrayList<>();
        url = "jdbc:mysql://localhost:3306/mybd";
        info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        driver = new com.mysql.cj.jdbc.Driver();
    }

    /**
     * 用来取操作后的返回结果，已经按字段切割好了
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> getSet()//取最终结果
    {
        return back;
    }
    private static Boolean sqlConnect() {
        try {
            conn = driver.connect(url, info);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Boolean sqlStop() {
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    /**
     *更新登录库所用的账号密码。
     * @param name 登录数据库用的账户
     * @param password 登录数据库要用的密码
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean infoChange(String name,String password)
    {
        try {
            info.clear();
            info.setProperty("user", name);
            info.setProperty("password", password);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 更新库的ip地址
     * @param in 要更新的ip地址
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean urlChange(String in)
    {
        try {
            url = in;
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 生成默认表
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean sqlMake()
    {
        try
        {
            sqlConnect();
            Statement st = conn.createStatement();
            st.execute(make1);
            st.execute(make2);
            st.execute(make3);
            st.execute(make4);
            st.execute(make5);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 对表中行进行删除操作
     * @param tableName 要删除行所在的表的名字
     * @param listName 删除条件的字段名
     * @param listValue 删除条件的字段值
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean sqlErase(String tableName, ArrayList<String> listName, ArrayList<String> listValue)
    {
        try {
            sqlConnect();
            if (listName.size() != listValue.size()||listName.size() == 0) {
                sqlStop();
                return false;
            }

            StringBuffer temper = new StringBuffer("delete from ");
            temper.append(tableName);
            if (listName.size() != 0) {
                temper.append(" where ");
                for (int i = 0; i < listName.size(); i++) {
                    if (i < listName.size() - 1) {
                        temper.append(listName.get(i));
                        temper.append("=");
                        temper.append("?");
                        temper.append(" and ");
                    } else {
                        temper.append(listName.get(i));
                        temper.append("=");
                        temper.append("?");
                    }
                }
            }
            String in = new String(temper);
            st1 = conn.prepareStatement(in);
            int figure = 1;
            for(int i = 0;i<listName.size();i++)
            {
                st1.setObject(figure,listValue.get(i));
                figure++;
            }
            st1.execute();
            st1.close();
        } catch (Exception e)
        {
            sqlStop();
            return false;
        }finally {
            sqlStop();
            return true;
        }
    }

    /**
     * 向表中添加一行数据，注意，如果添加的字段类型与值不匹配 或 字段要求不为空但是字段值为空会导致插入失败
     * @param tableName 要添加的表的名字
     * @param listName 要添加的字段名
     * @param listValue 要添加的字段值
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean sqlInsert(String tableName, ArrayList<String> listName, ArrayList<String> listValue)
    {
        try {
            sqlConnect();
            if (listName.size() != listValue.size()||listName.size() == 0) {
                sqlStop();
                return false;
            }
            StringBuffer temper = new StringBuffer("insert into ");
            String in;
            temper.append(tableName);
            temper.append(" ( ");
            for (int i = 0; i < listName.size(); i++) {
                if (i < listName.size() - 1) {
                    temper.append(listName.get(i));
                    temper.append(",");
                } else {
                    temper.append(listName.get(i));
                    temper.append(") values (");
                }
            }
            for (int i = 0; i < listValue.size(); i++) {
                if (i < listValue.size() - 1) {
                    temper.append("?");
                    temper.append(",");
                } else {
                    temper.append("?");
                    temper.append(")");
                }
            }
            in = new String(temper);
            st1 = conn.prepareStatement(in);
            int figure = 1;
            for(int i = 0;i<listValue.size();i++)
            {
                st1.setString(figure,listValue.get(i));
                figure+=1;
            }
            st1.executeUpdate();
            st1.close();
        }catch (Exception e)
        {
            sqlStop();
            return false;
        }
        finally {
            sqlStop();
            return true;
        }
    }

    /**
     *  对表中数据进行更改操作
     * @param tableName 要更改的表的名字
     * @param changeName 要更改的字段的名字
     * @param changeValue 要更改成的字段值
     * @param findName 更改字段的查找条件字段值
     * @param findValue 要更改的字段的查找条件字段值
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean sqlChange(String tableName, ArrayList<String>changeName, ArrayList<String>changeValue, ArrayList<String>findName, ArrayList<String>findValue)//修改，第一个表名，第二个更改的字段名，第三个更改的字段值，第四个更改的条件字段名，第五个更改的条件字段值
    {
        try {
            sqlConnect();
            StringBuffer temper = new StringBuffer("update ");
            temper.append(tableName);
            temper.append(" set ");
            if(changeName.size()==0||changeName.size()!=changeValue.size()||findName.size()!=findValue.size())
            {
                sqlStop();
                return false;
            }
            for(int i = 0;i<changeName.size();i++)
            {
                if(i<changeName.size()-1)
                {
                    temper.append(changeName.get(i));
                    temper.append("=");
                    temper.append("?");
                    temper.append(",");
                }
                else {
                    temper.append(changeName.get(i));
                    temper.append("=");
                    temper.append("?");
                }
            }
            if(findName.size()>0)
            {
                temper.append(" where ");
                for(int i = 0;i<findName.size();i++)
                {
                    if(i<findName.size()-1)
                    {
                        temper.append(findName.get(i));
                        temper.append("=");
                        temper.append("?");
                        temper.append(" and ");
                    }
                    else {
                        temper.append(findName.get(i));
                        temper.append("=");
                        temper.append("?");
                    }
                }
            }
            String in = new String(temper);
            st1 = conn.prepareStatement(in);
            int figure = 1;
            for(int i = 0;i<changeName.size();i++)
            {
                st1.setString(figure,changeValue.get(i));
                figure++;
            }
            if(findName.size()>0)
            {
                for(int i = 0;i<findValue.size();i++)
                {
                    st1.setString(figure,findValue.get(i));
                    figure++;
                }
            }
            st1.execute();
            st1.close();
        }catch (Exception e)
        {
            sqlStop();
            return false;
        }
        finally {
            sqlStop();
            return true;
        }
    }

    /**
     * 子查询函数，第一次查询的值作为第二次查询的条件
     * @param tableOut 第二次查询的表名
     * @param wantGet 第二次查找的字段名，即最终希望找到的，空代表全部查找
     * @param wantFindout  第二次查找的条件字段名
     * @param tableIn 第一次查找的表名
     * @param wantFindin 第一次查找的目标字段名
     * @param limit 第一次查找的条件字段名
     * @param limitValue 第一次查找的条件字段值
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public boolean sqlFindAnd(String tableOut, ArrayList<String>wantGet, String wantFindout, String tableIn, String wantFindin, ArrayList<String> limit, ArrayList<String> limitValue)
    {
        try{
            sqlConnect();
            StringBuffer temper = new StringBuffer("SELECT ");
            if(wantGet.isEmpty())
            {
                temper.append("*");
            }
            else
            {
                for(int i = 0;i<wantGet.size();i++)
                {
                    temper.append(wantGet.get(i));
                    if(i!=wantGet.size()-1)
                    {
                        temper.append(",");
                    }
                }
            }
            temper.append(" FROM ");
            temper.append(tableOut);
            temper.append(" WHERE ");
            temper.append(wantFindout);
            temper.append("=(SELECT ");
            temper.append(wantFindin);
            temper.append(" FROM ");
            temper.append(tableIn);
            temper.append(" WHERE ");
            for(int i = 0;i<limit.size();i++)
            {
                temper.append(limit.get(i));
                temper.append("=");
                temper.append(limitValue.get(i));
                if(i!= limit.size()-1)
                {
                    temper.append(",");
                }
            }
            temper.append(")");
            int figure = wantGet.size();
            String in = new String(temper);
            Statement st = conn.createStatement();
            ResultSet receive = st.executeQuery(in);
            ArrayList<String> nowtemper = new ArrayList<>();
            back.clear();
            if(figure == 0)
            {
                ResultSetMetaData rsmd = receive.getMetaData();
                figure = rsmd.getColumnCount();
            }
            while (receive.next()) {
                for (int i = 1; i <= figure; i++) {
                    nowtemper.add(String.valueOf(receive.getString(i)));
                }
                back.add(nowtemper);
                nowtemper = new ArrayList<>();
            }
            receive.close();
        }catch (Exception e)
        {
            sqlStop();
            return false;
        }
        finally {
            sqlStop();
            return true;
        }
    }

    /**
     * 对表进行查找操作，带有对结果的排序功能，默认乱序
     * @param tableName 要查找的表的名字
     * @param needFind 要查找的字段名
     * @param limit 要查找的限制条件的字段名
     * @param limitValue 要查找的限制条件的字段值
     * @param flagSort 是否排序，0不排序，1正序，-1倒序
     * @param sortNeed 排序依据的字段值
     * @return boolean，false表示运行出现错误，true表示运行成功
     */
    public Boolean sqlFind(String tableName, ArrayList<String>needFind, ArrayList<String> limit, ArrayList<String> limitValue, int flagSort, String sortNeed)
    {
        try {
            sqlConnect();
            Statement st = conn.createStatement();
            StringBuffer SQLstr = new StringBuffer("select ");
            int figure = 0;
            if(needFind.isEmpty())
            {
                SQLstr.append("*");
            }
            else
            {
                for(int i = 0;i<needFind.size();i++)
                {
                    SQLstr.append(needFind.get(i));
                    SQLstr.append(",");
                }
                figure = needFind.size();
            }
            SQLstr.append(" from ");
            SQLstr.append(tableName);
            if (limit.size() > 0) {
                SQLstr.append(" where ");
                for (int i = 0; i < limit.size(); i++) {
                    if (i < limit.size() - 1) {
                        SQLstr.append(limit.get(i));
                        SQLstr.append("=");
                        SQLstr.append(wl.addNeed(limitValue.get(i)));
                        SQLstr.append(" and ");
                    } else {
                        SQLstr.append(limit.get(i));
                        SQLstr.append("=");
                        SQLstr.append(wl.addNeed(limitValue.get(i)));
                    }
                }
            }
            if (flagSort != 0) {
                SQLstr.append(" order by ");
                SQLstr.append(sortNeed);
                if (flagSort == -1) {
                    SQLstr.append(" desc");
                }
            }
            String in = new String(SQLstr);
            ResultSet temperReceive = st.executeQuery(in);
            ArrayList<String> nowtemper = new ArrayList<>();
            back.clear();
            if(figure == 0)
            {
                //获取一行的列的数量
                ResultSetMetaData rsmd = temperReceive.getMetaData();
                figure = rsmd.getColumnCount();
            }
            while (temperReceive.next()) {
                for (int i = 1; i <= figure; i++) {
                    nowtemper.add(String.valueOf(temperReceive.getString(i)));
                }
                back.add(nowtemper);
                nowtemper = new ArrayList<>();
            }
            temperReceive.close();

        } catch (SQLException e) {
            sqlStop();
            return false;
        }
        finally {
            sqlStop();
            return true;//
        }

    }
}
