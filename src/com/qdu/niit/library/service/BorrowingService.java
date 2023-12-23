package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Borrowing;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface BorrowingService {
    /**
     * 仅判断这个人是否借过书
     * @param uid 要查找的人的id
     * @return true代表存在这个人的借书记录,false代表不存在这个人的借书记录
     * @throws SQLException
     */
    boolean ifHaveByUid(int uid)throws SQLException;

    /**
     * 仅判断这本书是否被借走
     * @param bid 要查找的书的名字
     * @return true代表这本书已被借走,false代表这本书没有被借走
     * @throws SQLException
     */
    boolean ifHaveByBid(int bid)throws SQLException;

    /**
     * 查找表中全部信息
     * @return Borrowing数组,未查找到则返回null
     * @throws SQLException
     */
    Borrowing[] findAll()throws SQLException;

    /**
     * 查找借书截止日期超过输入日期的借书记录
     * @param end_time
     * @return Borrowing数组,未查找到则返回null
     * @throws SQLException
     */
    Borrowing[] findAllByEndTime(LocalDateTime end_time)throws SQLException;

    /**
     * 查找一个人的借书记录
     * @param uid 人的id
     * @return Borrowing数组,未查找到则返回null
     * @throws SQLException
     */
    Borrowing[] findAllByUid(int uid)throws SQLException;

    /**
     * 查找一本书的借书记录
     * @param bid 书的id
     * @return Borrowing实体,未查找到则返回null
     * @throws SQLException
     */
    Borrowing findOneByBid(int bid)throws SQLException;

    /**
     * 向表中添加借书记录
     * @param in Borrowing实体
     * @return true代表添加成功,false代表当前记录中的书已经被别人借走了
     * @throws SQLException
     */
    boolean  insert(Borrowing in)throws SQLException;

    /**
     * 按人的id删除借书记录
     * @param uid 人的id
     * @return true代表成功,false代表不存在这个人
     * @throws SQLException
     */
    boolean  deleteByUid(int uid)throws SQLException;

    /**
     * 按书的id删除借书记录
     * @param bid 书的id
     * @return true代表成功,false代表不存在这本书
     * @throws SQLException
     */
    boolean  deleteByBid(int bid)throws SQLException;

    /**
     * 获取所有借书截止日期小于输入日期的借书记录
     * @param end_time 输入的日期
     * @return Borrowing数组
     * @throws SQLException
     */
    Borrowing[] findAllSmallEndTime(LocalDateTime end_time)throws SQLException;
}
