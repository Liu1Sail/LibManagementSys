package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;

import java.sql.SQLException;

/**
 * 数据访问对象接口，用于操作用户相关数据的数据库访问。
 */
public interface UserSQLDao extends BaseSQLDao {

    /**
     * 根据用户 ID 和密码获取用户对象。
     *
     * @param id       用户 ID
     * @param password 用户密码
     * @return 匹配用户 ID 和密码的用户对象，如果不存在则返回 null
     * @throws SQLException 如果数据库访问发生错误
     */
    User getUserByIdAndPassword(int id, String password) throws SQLException;

    /**
     * 根据用户名和密码获取用户对象。
     *
     * @param name     用户名
     * @param password 用户密码
     * @return 匹配用户名和密码的用户对象，如果不存在则返回 null
     * @throws SQLException 如果数据库访问发生错误
     */
    User getUserByNameAndPassword(String name, String password) throws SQLException;

    /**
     * 获取所有用户对象。
     *
     * @return 包含所有用户对象的数组
     * @throws SQLException 如果数据库访问发生错误
     */
    User[] getAllUsers() throws SQLException;

    /**
     * 更新用户的所有信息。
     *
     * @param user 包含更新信息的用户对象
     * @throws SQLException 如果数据库访问发生错误
     */
    void updateUserAll(User user) throws SQLException;

    /**
     * 根据用户 ID 更新用户密码。
     *
     * @param id       用户 ID
     * @param password 新密码
     * @throws SQLException 如果数据库访问发生错误
     */
    void updateUserPasswordById(int id, String password) throws SQLException;

    /**
     * 向数据库插入新的用户和用户信息。
     *
     * @param user 包含用户信息的用户对象
     * @param info 包含用户详细信息的用户信息对象
     * @return 插入操作的结果，通常是用户 ID
     * @throws SQLException 如果数据库访问发生错误
     */
    int insert(User user, UserInfo info) throws SQLException;

    /**
     * 向数据库插入新的用户。
     *
     * @param user 包含用户信息的用户对象
     * @return 插入操作的结果，通常是用户 ID
     * @throws SQLException 如果数据库访问发生错误
     */
    int insert(User user) throws SQLException;

    /**
     * 根据用户 ID 删除用户。
     *
     * @param id 用户 ID
     * @throws SQLException 如果数据库访问发生错误
     */
    void deleteById(int id) throws SQLException;

    boolean doesUserExistsByUserName(String userName)throws  SQLException;

    boolean doesUserExistsById(int id)throws SQLException;
}
