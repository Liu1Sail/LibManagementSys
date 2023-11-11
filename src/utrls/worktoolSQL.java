package utrls;

public class worktoolSQL
{
    public String addNeed(String in)//负责给传进来的字符串添加单引号
    {
        StringBuffer temper = new StringBuffer("'");
        temper.append(in);
        temper.append("'");
        String back = new String(temper);
        return back;
    }
}
