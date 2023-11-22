package user;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;

public interface UserInfoManager {
    public UserInfo getUserInfo(User user);
    public void updateUserInfo(UserInfo info);
    public void initialize();
}
