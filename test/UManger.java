import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.dao.imple.UserRepositoryManagerDaoImpl;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.service.UserService;
import com.qdu.niit.library.service.impl.UserServiceImpl;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.SQLException;

public class UManger {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");//配置数据库
            UserService ser = UserServiceImpl.getInstance();

            String userName = "liu";
            String password = "123";
            System.out.println(ser.userExists(userName));
            System.out.println(ser.login(userName , password));
            System.out.println(ser.getLocalUser());
            System.out.println(ser.getUserInfo(userName));
            ser.modifyLocalUserInfo("刘一帆" ,null , UserInfo.Gender.MALE , "15864158468" , "2311889551@qq.com");
            System.out.println(ser.getUserInfo(userName));
            ser.modifyLocalUserPassword("231398103");

            ser.logout();
            System.out.println(ser.login(userName , "231398103"));
    }
}
