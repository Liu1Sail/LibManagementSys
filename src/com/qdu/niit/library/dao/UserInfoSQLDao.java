package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.UserInfo;

import java.sql.SQLException;

/**
 * 数据访问对象接口，用于操作用户详细信息相关数据的数据库访问。
 */
public interface UserInfoSQLDao extends BaseSQLDao {

    /**
     * 更新用户详细信息的所有属性。
     *
     * @param info 包含更新信息的用户详细信息对象
     * @throws SQLException 如果数据库访问发生错误
     */
    void updateUserInfoAll(UserInfo info) throws SQLException;

    /**
     * 根据用户 ID 获取用户详细信息对象。
     *
     * @param id 用户 ID
     * @return 匹配用户 ID 的用户详细信息对象，如果不存在则返回 null
     * @throws SQLException 如果数据库访问发生错误
     */
    UserInfo getUserInfoById(int id) throws SQLException;

    /**
     * 根据用户名获取用户详细信息对象。
     *
     * @param userName 用户名
     * @return 匹配用户名的用户详细信息对象，如果不存在则返回 null
     * @throws SQLException 如果数据库访问发生错误
     */
    UserInfo getUserInfoByUserName(String userName) throws SQLException;
}