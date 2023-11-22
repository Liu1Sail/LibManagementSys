import com.qdu.niit.library.dao.imple.UserSQLDaoImpl;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.SQLException;

public class UManger {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "a231398103");//配置数据库
        try {
            UserSQLDaoImpl manager = UserSQLDaoImpl.getInstance();
            int id = manager.insert(new User(null, "liu", "123"));
            System.out.println(manager.getOneById(3));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
