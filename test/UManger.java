import user.UserManager;
import utils.SqlConfig;

import java.sql.SQLException;

public class UManger {
    public static void main(String[] args) throws SQLException {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");//配置数据库
        UserManager manager = UserManager.getInstance();
        manager.userSignUp("liu" , "123");
        System.out.println(manager.userLogin(1 , "123").getUname());
    }
}
