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
            String password1 = "123";
            String password2="231398103";
            if(!ser.userExists("liu"))
            {
                ser.register("liu" , password1,null,
                        null,
                        null,null,null);
            }
            if(null  != ser.login(userName , password1)){
                ser.modifyLocalUserPassword(password2);
            }
            else {
                ser.login(userName , password2);
                ser.modifyLocalUserPassword(password1);
            }

            System.out.println(ser.getLocalUser());


            UserInfo info = ser.getUserInfo(ser.getLocalUser().getUName());
            System.out.println(info);
            if(info.getName() != null){
                ser.modifyLocalUserInfo(null,
                        null,
                        null,null,null);
            }else {
                ser.modifyLocalUserInfo("刘一帆" ,null , UserInfo.Gender.MALE , "15864158468" , "2311889551@qq.com");

            }

            ser.logout();
            System.out.println(ser.getLocalUser());

//            System.out.println(ser.login(userName , password1));
//            System.out.println(ser.getLocalUser());
//            System.out.println(ser.getUserInfo(userName));
//            System.out.println(ser.getUserInfo(userName+"not"));
//            ser.modifyLocalUserInfo("刘一帆" ,null , UserInfo.Gender.MALE , "15864158468" , "2311889551@qq.com");
//            System.out.println(ser.getUserInfo(userName));
//            ser.modifyLocalUserPassword("231398103");
//
//            ser.logout();
//            System.out.println(ser.getLocalUser());
//            System.out.println(ser.login(userName , "231398103"));
    }
}
