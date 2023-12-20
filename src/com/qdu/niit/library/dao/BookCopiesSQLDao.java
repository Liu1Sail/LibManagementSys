package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.BookCopy;

import java.sql.SQLException;

public interface BookCopiesSQLDao {

    /**
     * 返回主键，向BookCopies中插入
     * 在insert函数中已经初始化BookCopy
     */
    Integer insertToBookCopies(BookCopy element) throws SQLException;

    /**
     * 每次对在架状态取反
     * @param copy_id
     * @throws SQLException
     */
    void changeOnShelfStatus(Integer copy_id) throws SQLException;

    /**
     * 获得书籍是否可见
     *
     * @param copy_id
     * @return
     */
    boolean getIsVisibleByCopyID(Integer copy_id) throws SQLException;

    /**
     * 返回book_id
     *
     * @param copy_id
     */
    void delete(Integer copy_id) throws SQLException;
    boolean getOnShelfStatus(Integer copy_id) throws SQLException;
}
