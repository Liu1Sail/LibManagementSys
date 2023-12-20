package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.User;

public interface SuperUserService extends BaseUserService{
    public int superUserRegister(String userName , String password);
    public User superUserLogin(String userName , String password);
    public User superUserLogin(int id , String password);
    /**
     * 判断指定用户名的用户是否存在。
     *
     * @param userName 用户名
     * @return 如果用户存在则返回 true，否则返回 false
     */
    boolean superUserExists(String userName);








    //============================继承=================================//
    @Override
    User getLocalUser();
    @Override
    void logout();
    @Override
    boolean modifyLocalUserPassword(String newPassword);
    @Override
    boolean isLocalUserPasswordCorrect();

    @Override
    boolean isLocalUserSuperUser();
}
