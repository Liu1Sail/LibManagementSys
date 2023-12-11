import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.dao.imple.UserRepositoryManagerDaoImpl;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.SQLException;

public class UManger {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");//配置数据库
        try {
            UserSQLDao manager = UserRepositoryManagerDaoImpl.getInstance();
            UserInfoSQLDao infoManager = UserRepositoryManagerDaoImpl.getInstance();
            try {
                manager.insert(new User(null, "liuLi", "123"));
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

            User user = manager.getUserByNameAndPassword("liuLi", "123");
            System.out.println(user+"\n"+infoManager.getUserInfoById(user.getUID()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
