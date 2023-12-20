package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.dao.imple.SuperUserManagerDaoImpl;
import com.qdu.niit.library.dao.imple.UserRepositoryManagerDaoImpl;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.service.SuperUserService;
import com.qdu.niit.library.service.UserService;

import java.sql.SQLException;
import java.util.Date;

public class UserServiceImpl implements UserService , SuperUserService {
    @Override
    public boolean superUserExists(String userName) {
        assert userName!=null : "userName不能为空";
        try{
            UserSQLDao dao = SuperUserManagerDaoImpl.getInstance();
            return dao.doesUserExistsByUserName(userName);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLocalUserSuperUser() {
        return null != localUser && localUser.isSuperUser();
    }

    @Override
    public int superUserRegister(String userName, String password) {
        assert userName !=null && password != null : "userName 和 password不能为空";
        try{
            UserSQLDao dao = SuperUserManagerDaoImpl.getInstance();
            return dao.insert(new User(null , userName , password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User superUserLogin(String userName, String password) {
        assert userName!=null && password != null : "userName 和 password不能为空";
        try {
            UserSQLDao dao = SuperUserManagerDaoImpl.getInstance();
            localUser = dao.getUserByNameAndPassword(userName , password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return localUser;
    }

    @Override
    public User superUserLogin(int id, String password) {
        assert  password != null : "password不能为空";
        try {
            UserSQLDao dao = SuperUserManagerDaoImpl.getInstance();
            localUser = dao.getUserByIdAndPassword(id , password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return localUser;
    }

    @Override
    public int register(String userName, String password, String name, Date birth, UserInfo.Gender gender, String phone, String email) {
        assert userName !=null && password != null : "userName 和 password不能为空";
        try{
            UserSQLDao dao = UserRepositoryManagerDaoImpl.getInstance();
            return dao.insert(new User(null ,userName , password) , new UserInfo(null , name , birth , gender , phone ,email));
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getLocalUser() {
        return localUser;
    }
    @Override
    public void logout()
    {
        localUser = null;
    }
    @Override
    public User login(String userName, String password) {
        assert userName!=null && password != null : "userName 和 password不能为空";
        try {
            UserSQLDao dao = UserRepositoryManagerDaoImpl.getInstance();
            localUser = dao.getUserByNameAndPassword(userName , password);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return localUser;
    }

    @Override
    public User login(int id, String password) {
        assert password != null : "password不能为空";
        try {
            UserSQLDao dao = UserRepositoryManagerDaoImpl.getInstance();
            localUser = dao.getUserByIdAndPassword(id , password);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return localUser;
    }

    @Override
    public UserInfo getUserInfo(String userName) {
        assert userName!=null : "userName不能为空";
        if(localUser.isSuperUser())return null;
        try {
            return UserRepositoryManagerDaoImpl.getInstance().getUserInfoByUserName(userName);
        }catch (SQLException e)
        {
            return null;
        }
    }

    @Override
    public boolean modifyLocalUserInfo(String name, Date birth, UserInfo.Gender gender, String phone, String email) {
        if(getLocalUser() == null || getLocalUser().isSuperUser())return false;
        try {
            UserInfoSQLDao dao = UserRepositoryManagerDaoImpl.getInstance();
            dao.updateUserInfoAll(new UserInfo(localUser.getUID() ,
                    name ,
                    birth ,
                    gender ,
                    phone ,
                    email)
            );
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifyLocalUserPassword(String newPassword) {
        assert  newPassword != null : "password不能为空";
        if(getLocalUser()!=null){
            try {
                UserSQLDao dao;
                if(localUser.isSuperUser())
                    dao = SuperUserManagerDaoImpl.getInstance();
                else
                    dao = UserRepositoryManagerDaoImpl.getInstance();
                dao.updateUserPasswordById(localUser.getUID() , newPassword);
                return true;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        } else return false;
    }
    @Override
    public boolean isLocalUserPasswordCorrect(){
        if(localUser == null)return false;
        try{
            UserSQLDao dao;
            if(localUser.isSuperUser())
                dao = SuperUserManagerDaoImpl.getInstance();
            else
                dao = UserRepositoryManagerDaoImpl.getInstance();
            return null != dao.getUserByIdAndPassword(
                    getLocalUser().getUID() ,
                    getLocalUser().getUPassword()
            );
        }catch (SQLException e){
           e.printStackTrace();
        }
        return false;
    }
    public boolean userExists(String userName)
    {
        assert userName!=null : "userName不能为空";
        try{
            UserSQLDao dao = UserRepositoryManagerDaoImpl.getInstance();
            return dao.doesUserExistsByUserName(userName);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static UserServiceImpl getInstance() {
        if(instance == null)
            instance = new UserServiceImpl();
        return instance;
    }

    private UserServiceImpl(){
    }

    private User localUser;
    private static UserServiceImpl instance;
}
