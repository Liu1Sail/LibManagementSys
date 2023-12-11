package com.qdu.niit.library.dao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据访问对象接口，提供基本的数据库操作方法。
 */
public interface BaseSQLDao {

    /**
     * 执行 SQL 更新语句，返回受影响的行数。
     *
     * @param SQL  SQL 更新语句
     * @param args 更新语句的参数
     * @return 受影响的行数
     * @throws SQLException 如果数据库访问发生错误
     */
    int executeUpdate(final String SQL, Object... args) throws SQLException;

    /**
     * 执行 SQL 更新语句并获取生成的键值，返回包含键值的列表。
     *
     * @param SQL  SQL 更新语句
     * @param args 更新语句的参数
     * @return 包含生成的键值的列表
     * @throws SQLException 如果数据库访问发生错误
     */
    ArrayList<Object[]> executeUpdateAndGetGeneratedKeys(String SQL, Object... args) throws SQLException;

    /**
     * 执行 SQL 查询语句，返回单行结果。
     *
     * @param SQL  SQL 查询语句
     * @param args 查询语句的参数
     * @return 包含单行结果的对象数组，如果查询不到则返回 null
     * @throws SQLException 如果数据库访问发生错误
     */
    Object[] getOne(final String SQL, Object... args) throws SQLException;

    /**
     * 执行 SQL 查询语句，返回多行结果。
     *
     * @param SQL  SQL 查询语句
     * @param args 查询语句的参数
     * @return 包含多行结果的对象数组列表，如果查询不到则返回空 ArrayList
     * @throws SQLException 如果数据库访问发生错误
     */
    ArrayList<Object[]> getMany(final String SQL, Object... args) throws SQLException;

    /**
     * 将对象转换为指定类型。
     *
     * @param obj 要转换的对象
     * @param <T> 目标类型
     * @return 转换后的对象，如果转换失败则返回 null
     */
    <T> T Cast(Object obj);
}