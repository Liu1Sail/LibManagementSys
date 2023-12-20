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

    /**
     * 获取本地用户对象。
     *
     * @return 本地用户对象
     */
    @Override
    User getLocalUser();
    /**
     * 注销(登出)当前用户。
     */
    @Override
    void logout();

    /**
     * 修改本地用户密码。
     *
     * @param newPassword  新的密码
     * @return 修改是否成功
     */
    @Override
    boolean modifyLocalUserPassword(String newPassword);

    /**
     * 验证本地用户密码是否正确。
     *
     * @return 验证结果，true 表示密码正确，false 表示密码不正确
     */
    @Override
    boolean isLocalUserPasswordCorrect();

    @Override
    boolean isLocalUserSuperUser();
}
