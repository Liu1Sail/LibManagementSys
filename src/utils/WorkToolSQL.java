package utils;

public class WorkToolSQL
{
    public String addNeed(String in)//负责给传进来的字符串添加单引号
    {
        StringBuffer temper = new StringBuffer("'");
        temper.append(in);
        temper.append("'");
        String back = new String(temper);
        return back;
    }
    public String make(int a,String a1,int b,String b1)
    {
        StringBuffer back = new StringBuffer(a1);
        back.append("数量为");
        back.append(a);
        back.append(b1);
        back.append("数量为");
        back.append(b);
        return new String(back);
    }
}
