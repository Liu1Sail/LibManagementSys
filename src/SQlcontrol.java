import utrls.worktoolSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SQlcontrol {
    public ArrayList<ArrayList<String>> back;
    private static Connection conn;
    private worktoolSQL wl;
    private static Statement st;

    public SQlcontrol() {
        wl = new worktoolSQL();
        conn = null;
        back = new ArrayList<>();
    }

    public static Boolean SQLconnect() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            String url = "jdbc:mysql://localhost:3306/mybd";
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            conn = driver.connect(url, info);
            st = conn.createStatement();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean SQLstop() {
        try {
            st.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean SQLerase(String tableName, ArrayList<String> listName, ArrayList<String> listValue) {
        SQLconnect();
        StringBuffer temper = new StringBuffer("delete from ");
        temper.append(tableName);
        if (listName.size() != listValue.size()) {
            SQLstop();
            return false;
        }
        if (listName.size() != 0) {
            temper.append(" where ");
        }
        for (int i = 0; i < listName.size(); i++) {
            if (i < listName.size() - 1) {
                temper.append(listName.get(i));
                temper.append("=");
                temper.append(wl.addNeed(listValue.get(i)));
                temper.append(" and ");
            } else {
                temper.append(listName.get(i));
                temper.append("=");
                temper.append(wl.addNeed(listValue.get(i)));
            }
        }
        String in = new String(temper);
        try {
            st.execute(in);
        } catch (Exception e)
        {
           SQLstop();
           return false;
        }
        SQLstop();
        return true;
    }

    public boolean SQLinsert(String tableName, ArrayList<String> listName, ArrayList<String> listValue) throws SQLException//要添加的表名字，要添加的字段名，要添加的字段值
    {
        SQLconnect();
        if (listName.size() != listValue.size()) {
            SQLstop();
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
                temper.append(wl.addNeed(listValue.get(i)));
                temper.append(",");
            } else {
                temper.append(wl.addNeed(listValue.get(i)));
                temper.append(")");
            }
        }
        in = new String(temper);
        st.executeUpdate(in);
        SQLstop();
        return true;
    }
    public boolean SQLchange(String tableName,ArrayList<String>changeName,ArrayList<String>changeValue,ArrayList<String>findName,ArrayList<String>findValue)
    {
        try {
            SQLconnect();
            StringBuffer temper = new StringBuffer("update ");
            temper.append(tableName);
            temper.append(" set ");
            if(changeName.size()!=changeValue.size()||findName.size()!=findValue.size())
            {
                SQLstop();
                return false;
            }
            for(int i = 0;i<changeName.size();i++)
            {
                if(i<changeName.size()-1)
                {
                    temper.append(changeName.get(i));
                    temper.append("=");
                    temper.append(wl.addNeed(changeValue.get(i)));
                    temper.append(",");
                }
                else {
                    temper.append(changeName.get(i));
                    temper.append("=");
                    temper.append(wl.addNeed(changeValue.get(i)));
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
                        temper.append(wl.addNeed(findValue.get(i)));
                        temper.append(" and ");
                    }
                    else {
                        temper.append(findName.get(i));
                        temper.append("=");
                        temper.append(wl.addNeed(findValue.get(i)));
                    }
                }
            }
            String in = new String(temper);
            st.execute(in);
        }catch (Exception e)
        {
            SQLstop();
            return false;
        }
        SQLstop();
        return true;
    }
    //子查询,用于学生搜索课程和课程搜索学生
    //              tableOut                 tableIn                              limit     limitValue
    //SELECT * FROM course  WHERE id=(SELECT courseid FROM studentandcourse WHERE studentid=2002)
    public boolean SQLfindand(int figure,String tableOut,String tableIn,String limit,String limitValue)
    {
        SQLconnect();
        StringBuffer temper = new StringBuffer("SELECT * FROM ");
        temper.append(tableOut);
        temper.append(" WHERE id=(SELECT ");
        temper.append(tableIn);
        temper.append(" FROM studentandcourse WHERE ");
        temper.append(limit);
        temper.append("=");
        temper.append(limitValue);
        temper.append(")");
        String in = new String(temper);
        try{
            ResultSet receive = st.executeQuery(in);
            ArrayList<String> nowtemper = new ArrayList<>();
            back.clear();
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
            SQLstop();
            return false;
        }
        SQLstop();
        return true;
    }
    //tableName是指要查询哪张表,figure记录这个表有多少列,limit是指限定条件如,年级,班,limitValue是记录几年级几班,与limit相对应,flagSort表示是否排序，0不排序，-1倒序，1正序,sortNeed按哪个排序
    //经过测试,正确
    public int SQLfind(String tableName, ArrayList<String>needFind, ArrayList<String> limit, ArrayList<String> limitValue, int flagSort, String sortNeed) {
        //防注入
        SQLconnect();
        try {
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
            SQLstop();
            return -1;
        }
        SQLstop();
        return 1;
    }
}
