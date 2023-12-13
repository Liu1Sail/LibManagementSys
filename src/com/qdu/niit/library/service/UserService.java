package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;

import java.util.Date;

/**
 * 用户服务接口定义了一系列与用户相关的操作。
 */
public interface UserService {

    /**
     * 注册新用户并保存用户信息。
     *
     * @param userName 用户名
     * @param password 用户密码
     * @param name     用户姓名
     * @param birth    用户生日
     * @param gender   用户性别
     * @param phone    用户电话
     * @param email    用户邮箱
     * @return 注册操作的结果，通常是用户 ID 注册失败返回-1
     */
    int register(String userName, String password, String name, Date birth, UserInfo.Gender gender, String phone, String email);

    /**
     * 获取本地用户对象。
     *
     * @return 本地用户对象
     */
    User getLocalUser();

    /**
     * 用户登录操作。
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 登录成功时返回用户对象，否则返回 null
     */
    User login(String userName, String password);

    /**
     * 通过用户 ID 和密码进行登录。
     *
     * @param id       用户 ID
     * @param password 用户密码
     * @return 登录成功时返回用户对象，否则返回 null
     */
    User login(int id, String password);

    /**
     * 注销(登出)当前用户。
     */
    void logout();

    /**
     * 获取指定用户名的用户信息。
     *
     * @param userName 用户名
     * @return 用户信息对象，如果用户不存在则返回 null
     */
    UserInfo getUserInfo(String userName);

    /**
     * 修改本地用户信息。
     *
     * @param name     新的姓名
     * @param birth    新的生日
     * @param gender   新的性别
     * @param phone    新的电话
     * @param email    新的邮箱
     * @return 修改是否成功
     */
    boolean modifyLocalUserInfo(String name, Date birth, UserInfo.Gender gender, String phone, String email);

    /**
     * 修改本地用户密码。
     *
     * @param newPassword  新的密码
     * @return 修改是否成功
     */
    boolean modifyLocalUserPassword(String newPassword);

    /**
     * 验证本地用户密码是否正确。
     *
     * @return 验证结果，true 表示密码正确，false 表示密码不正确
     */
    boolean isLocalUserPasswordCorrect();

    /**
     * 判断指定用户名的用户是否存在。
     *
     * @param userName 用户名
     * @return 如果用户存在则返回 true，否则返回 false
     */
    boolean userExists(String userName);
}
