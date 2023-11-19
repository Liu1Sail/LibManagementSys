import user.User;
import user.UserManager;
import utils.sqlConfig;

import java.sql.SQLException;

public class UManger {
    public static void main(String[] args) throws SQLException {
        sqlConfig config = sqlConfig.getInstance();
        config.init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");
        UserManager manager = UserManager.getInstance();
        manager.userSignUp("liu" , "123");
        System.out.println(manager.userLogin(1 , "123").getUname());
    }
}
