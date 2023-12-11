import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.dao.imple.UserInfoSQLDaoImpl;
import com.qdu.niit.library.dao.imple.UserSQLDaoImpl;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.SQLException;
import java.util.Date;

public class UManger {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");//配置数据库
        try {
            UserSQLDao manager = UserSQLDaoImpl.getInstance();
            UserInfoSQLDao infoManager = UserInfoSQLDaoImpl.getInstance();
            try {
                manager.insert(new User(null, "liu", "123"));
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

            User user = manager.getOneByNameAndPassword("liu", "123");


            try {
                infoManager.insert(new UserInfo(user.getUID(),
                                "刘一帆",
                        null,
                                UserInfo.Gender.MALE,
                                "15864158468",
                                "2311889551@qq.com"
                        )
                );
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

            System.out.println(user+"\n"+infoManager.getOneById(user.getUID()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
