package utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
public class SqlControl {
    private ArrayList<ArrayList<String>> back;
    private static Connection conn;
    private WorkToolSQL wl;//
    static Driver driver;
    static Properties info;
    static String url;
    private static final String make1 = "CREATE TABLE IF NOT EXISTS Personal_Information(uid INT NOT NULL,uname VARCHAR(20) NOT NULL,uage INT,ugender INT,uphone VARCHAR(20),uemail VARCHAR(50));";
    private static final String make2 = "CREATE TABLE  IF NOT EXISTS Account(uid INT NOT NULL,uname VARCHAR(30) NOT NULL,upawd VARCHAR(30) NOT NULL);";
    private static final String make3 = "CREATE TABLE  IF NOT EXISTS Book_Information(bid INT NOT NULL,bname VARCHAR(50) NOT NULL,bauthor VARCHAR(50),bcategory VARCHAR(20),bamount INT,bposition VARCHAR(20));";
    private static final String make4 = "CREATE TABLE  IF NOT EXISTS Borrowing_Information(uid INT NOT NULL,bid INT NOT NULL,start_time DATETIME NOT NULL,end_time DATETIME NOT NULL);";
    private static final String make5 = "CREATE TABLE  IF NOT EXISTS Room_LIst(rid INT NOT NULL,rname INT NOT NULL,rfloor INT NOT NULL);";
    private static PreparedStatement st1;

    /**
     *  *在调用时会测试链接数据库，保证正常链接
     * @throws SQLException driver链接有误
     * @throws connectWrong 数据库链接失败，请检查
     * @throws closeWrong
     */
    public SqlControl() throws SQLException,connectWrong, closeWrong {
        SqlConfig use = SqlConfig.getInstance();//
        wl = new WorkToolSQL();
        conn = null;
        back = new ArrayList<>();
        url = use.getUrl();
        info = new Properties();
        info.setProperty("user", use.getUser());
        info.setProperty("password", use.getPassword());
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用来取操作后的返回结果，已经按字段切割好了
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> getSet()//取最终结果
    {
        return back;
    }
    private static void sqlConnect() throws connectWrong, SQLException {
        try {
            conn = driver.connect(url, info);
        } catch (Exception e) {
            conn = driver.connect(url, info);
            if(conn!=null)
            {
                return;
            }
            throw new connectWrong(e);
        }
    }

    private static void sqlStop() throws closeWrong, SQLException {
        try {
            if(conn.isClosed())
            {
                return;
            }
            conn.close();
        } catch (Exception e) {
            if(conn.isClosed())
            {
                return;
            }
            throw new closeWrong(e);
        }

    }

    /**
     * 为了处理断开链接失败的错误所提供的函数，当收到断开链接失败的错误stopWrong时，请调用此函数，如果本函数抛出stopWrong，那就是你的事情了,
     * @throws closeWrong 再次尝试断开链接失败
     */
    public void wrongStopControl() throws closeWrong
    {
        try {
            if(conn.isClosed())
            {

            }
            else
            {
                conn.close();
            }
        } catch (SQLException e) {
            throw new closeWrong(e);
        }
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
     * @return 无错误抛出即为正常
     * @throws SQLException 发生数据库访问错误
     * @throws pushWrong 输入的数据listName为空或者listName与listValue数量不匹配
     * @throws connectWrong 链接数据库时出错
     * @throws closeWrong 断开链接时出错
     * @throws SQLTimeoutException 当驱动程序确定超过了setQueryTimeout方法指定的超时值，并且至少尝试取消当前运行的Statement时
     */
    public void sqlErase (String tableName, ArrayList<String> listName, ArrayList<String> listValue) throws SQLTimeoutException,SQLException,pushWrong,connectWrong, closeWrong
    {
        try {
            sqlConnect();
            if (listName.size() != listValue.size()||listName.size() == 0) {
                sqlStop();
                throw new pushWrong(new Exception(wl.make(listName.size(),"listName",listValue.size(),"listValue")));
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

        } catch (connectWrong e)
        {
            throw new connectWrong(new Exception("链接数据库时出错"));
        }
        catch (closeWrong e)
        {
            throw new closeWrong(new Exception("断开链接时出错"));
        }
        catch (SQLTimeoutException e)
        {
            throw new SQLTimeoutException(e);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        finally {
            sqlStop();
        }
    }

    /**
     * 向表中添加一行数据，注意，如果添加的字段类型与值不匹配 或 字段要求不为空但是字段值为空会导致插入失败
     * @param tableName 要添加的表的名字
     * @param listName 要添加的字段名
     * @param listValue 要添加的字段值
     * @return 无错误抛出即为正常
     * @throws SQLException 发生数据库访问错误
     * @throws pushWrong 输入的数据listName为空或者listName与listValue数量不匹配
     * @throws connectWrong 链接数据库时出错
     * @throws closeWrong 断开链接时出错
     * @throws SQLTimeoutException 当驱动程序确定超过了setQueryTimeout方法指定的超时值，并且至少尝试取消当前运行的Statement时
     */
    public void sqlInsert(String tableName, ArrayList<String> listName, ArrayList<String> listValue)throws connectWrong, closeWrong,pushWrong,SQLException,SQLTimeoutException
    {
        try {
            sqlConnect();
            if (listName.size() != listValue.size()||listName.size() == 0) {
                sqlStop();
                throw new pushWrong(new Exception(wl.make(listName.size(),"listName",listValue.size(),"listValue")));
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
        }catch (connectWrong e)
        {
            throw new connectWrong(new Exception("链接数据库时出错"));
        }
        catch (closeWrong e)
        {
            throw new closeWrong(new Exception("断开链接时出错"));
        }
        catch (SQLTimeoutException e)
        {
            throw new SQLTimeoutException(e);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        finally {
            sqlStop();
        }
    }

    /**
     *  对表中数据进行更改操作
     * @param tableName 要更改的表的名字
     * @param changeName 要更改的字段的名字
     * @param changeValue 要更改成的字段值
     * @param findName 更改字段的查找条件字段值,可以为空
     * @param findValue 要更改的字段的查找条件字段值,可以为空
     * @throws SQLException 发生数据库访问错误
     * @throws pushWrong 输入的数据listName为空或者listName与listValue数量不匹配,或者findName与findValue数量不匹配
     * @throws connectWrong 链接数据库时出错
     * @throws closeWrong 断开链接时出错
     * @throws SQLTimeoutException 当驱动程序确定超过了setQueryTimeout方法指定的超时值，并且至少尝试取消当前运行的Statement时
     */
    public void sqlChange(String tableName, ArrayList<String>changeName, ArrayList<String>changeValue, ArrayList<String>findName, ArrayList<String>findValue)throws connectWrong, closeWrong,SQLException,pushWrong,SQLTimeoutException
    {
        try {
            sqlConnect();
            StringBuffer temper = new StringBuffer("update ");
            temper.append(tableName);
            temper.append(" set ");
            if(changeName.size()==0||changeName.size()!=changeValue.size())
            {
                sqlStop();
                throw new pushWrong(new Exception(wl.make(changeName.size(),"changeName",changeValue.size(),"changeValue")));
            }
            else if(findName.size()!=findValue.size())
            {
                throw new pushWrong(new Exception(wl.make(findName.size(),"findName",findValue.size(),"findValue")));
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
        }catch (connectWrong e)
        {
            throw new connectWrong(new Exception("链接数据库时出错"));
        }
        catch (closeWrong e)
        {
            throw new closeWrong(new Exception("断开链接时出错"));
        }
        catch (SQLTimeoutException e)
        {
            throw new SQLTimeoutException(e);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        finally {
            sqlStop();
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
     * @throws SQLException 发生数据库访问错误
     * @throws connectWrong 链接数据库时出错
     * @throws closeWrong 断开链接时出错
     * @throws SQLTimeoutException 当驱动程序确定超过了setQueryTimeout方法指定的超时值，并且至少尝试取消当前运行的Statement时
     */
    public void sqlFindAnd(String tableOut, ArrayList<String>wantGet, String wantFindout, String tableIn, String wantFindin, ArrayList<String> limit, ArrayList<String> limitValue)throws connectWrong, closeWrong,SQLException,SQLTimeoutException
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
        }catch (connectWrong e)
        {
            throw new connectWrong(new Exception("链接数据库时出错"));
        }
        catch (SQLTimeoutException e)
        {
            throw new SQLTimeoutException(e);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        finally {
            sqlStop();
        }
    }

    /**
     * 对表进行查找操作，带有对结果的排序功能，默认乱序
     * @param tableName 要查找的表的名字
     * @param needFind 要查找的字段名,可以为空
     * @param limit 要查找的限制条件的字段名,可以为空
     * @param limitValue 要查找的限制条件的字段值,可以为空
     * @param flagSort 是否排序，0不排序，1正序，-1倒序
     * @param sortNeed 排序依据的字段值，flagSort=0时,可以为空
     * @throws SQLException 发生数据库访问错误
     * @throws connectWrong 链接数据库时出错
     * @throws closeWrong 断开链接时出错
     * @throws SQLTimeoutException 当驱动程序确定超过了setQueryTimeout方法指定的超时值，并且至少尝试取消当前运行的Statement时
     */
    public void sqlFind(String tableName, ArrayList<String>needFind, ArrayList<String> limit, ArrayList<String> limitValue, int flagSort, String sortNeed)throws connectWrong, closeWrong,SQLException,SQLTimeoutException
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

        } catch (connectWrong e)
        {
            throw new connectWrong(new Exception("链接数据库时出错"));
        }
        catch (SQLTimeoutException e)
        {
            throw new SQLTimeoutException(e);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        finally {
            sqlStop();
        }
    }
}
