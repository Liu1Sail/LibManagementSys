package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ReturningService {
    /**
     * 判断这个人是否有还书记录
     * @param uid 人的id
     * @return true代表存在这个人的还书记录,false代表不存在这个人的还书记录
     * @throws SQLException
     */
    boolean ifHaveByUid(int uid)throws SQLException;

    /**
     * 判断这本书是否存在归还记录
     * @param bid 书的id
     * @return true代表这本书被归还过,false代表这本书未被归还
     * @throws SQLException
     */
    boolean ifHaveByBid(int bid)throws SQLException;

    /**
     * 查找所有归还时间小于输入时间的归还记录
     * @param end_time 输入的时间
     * @return Returning数组,未查找到则返回null
     * @throws SQLException
     */
    Returning[] findAllByEndTime(LocalDateTime end_time)throws SQLException;

    /**
     * 查找一个人的归还记录
     * @param uid 人的id
     * @return Returning数组,未查找到则返回null
     * @throws SQLException
     */
    Returning[] findAllByUid(int uid)throws SQLException;

    /**
     * 查找一本书的归还记录
     * @param bid 书的id
     * @return Returning数组,未查找到则返回null
     * @throws SQLException
     */
    Returning[] findAllByBid(int bid)throws SQLException;

    /**
     * 添加新的还书记录,不能通知你添加成功与否
     * @param in
     * @throws SQLException
     */
    void  insert(Returning in)throws SQLException;

    /**
     * 按人和书的id查找还书记录
     * @param uid 人的id
     * @param bid 书的id
     * @return Returning数组,未查找到则返回null
     * @throws SQLException
     */
    Returning[] findAllByUidAndBid(int uid,int bid)throws SQLException;
}
