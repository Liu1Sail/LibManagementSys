package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.User;

public interface BaseUserService {
    boolean isLocalUserSuperUser();
    /**
     * 获取本地用户对象。
     *
     * @return 本地用户对象
     */
    User getLocalUser();
    /**
     * 注销(登出)当前用户。
     */
    void logout();

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
}
