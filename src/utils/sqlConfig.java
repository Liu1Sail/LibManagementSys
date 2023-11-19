package utils;

/**
 * <p>用来存储sql配置的类, 比如: url,user以及password. <br> 目的为了实现数据全局共享</p>
 */
public class sqlConfig {

    /**
     * <p style="color : red">用来存储参数需要通过init(),set*()函数来设置</p>
     * @return mysql配置实例
     */
    public sqlConfig getInstance()
    {
        if(instance == null){
            instance = new sqlConfig();
        }
        return instance;
    }

    /**
     * @return <p style="color : green">获取url</p>
     */
    public String getUrl(){return m_Url;}
    /**
     * @return <p style="color : green">获取user</p>
     */
    public String getUser(){return m_User;}

    /**
     * @return <p style="color : green">获取user的password</p>
     */
    public String getPassword(){return m_Password;}

    /**
     *
     * @param url 设置url
     * @param user 设置user
     * @param password 设置password
     */
    public void init(String url , String user , String password)
    {
        m_Url = url;
        m_User = user;
        m_Password = password;
    }

    /**
     * @param url 设置url
     */
    public void setUrl(String url){m_User = url;}

    /**
     * @param user 设置user
     */
    public void setUser(String user){m_User = user;}

    /**
     * @param password 设置password
     */
    public void setPassword(String password){m_Password = password;}



    //-------------------------------------------------------------------------------//
    private static sqlConfig instance;
    private sqlConfig(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private String m_Url;
    private String m_User;
    private String m_Password;
}
