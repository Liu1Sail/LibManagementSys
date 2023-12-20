package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.entity.BookInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface BookRepositorySQLDao {
    /**
     * 将一组 {@code BookInfo} 对象插入数据库，确保事务的完整性。
     *
     * @param insertArrayOutGet 要插入的 {@code BookInfo} 对象列表。
     * @throws SQLException            如果发生数据库访问错误。
     */
    void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException;

    /**
     * 从三个表删除一个copy，是不要这本图书了，图书要出馆
     * @param copy_ids 书的ids
     * @throws SQLException 有问题
     */
    void delete(ArrayList<Integer> copy_ids) throws SQLException;

    /**
     * 返回的是一本书但是用ArrayList封装了
     * @param copy_id 目标
     * @return 一本书
     * @throws SQLException 有问题
     */
    ArrayList<BookInfo> getBookByCopyID(Integer copy_id) throws SQLException;

    void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException;

    void updateBookLocationByCopyID(Integer copy_id, String location) throws SQLException;  //需要回滚

    void updateAuthorByBookID(Integer book_id, String author) throws SQLException;

    void updatePublisherByBookID(Integer book_id, String publisher) throws SQLException;

    void updateReceiptDateByBookID(Integer book_id, Date date) throws SQLException;

    void updateGenreByBookID(Integer book_id, String genre) throws SQLException;

    /**
     * 根据插入时间获得同一时间插入的所有图书
     * @param date 时间
     * @return 所有书（copy）
     * @throws SQLException 有问题
     */
    ArrayList<BookInfo> getBookByDate(Date date) throws SQLException;

    /**
     * 根据book_id获得所有的copy
     * @param book_id 目标书的id
     * @return 所有书（copy）
     * @throws SQLException 有问题
     */
    ArrayList<BookInfo> getBookByBookID(Integer book_id) throws SQLException;

    /**
     * 根据author关键字获得书籍信息
     * @param author 目标书的作者
     * @return book 返回一类书
     * @throws SQLException 有问题
     */
    ArrayList<Book> getBookByAuthor(String author) throws SQLException;

    /**
     * 根据title关键字获得书籍信息
     * @param title 书的名字
     * @return book 返回一类书
     * @throws SQLException 有问题
     */
    ArrayList<Book> getBookByTitle(String title) throws SQLException;

    ArrayList<Book> getBookByAuthorAndTitle(String author, String title) throws SQLException;

    /**
     * 每次对一本书的在架状态取反
     * @param copy_id 目标id
     * @throws SQLException 有问题
     */
    void changeOnShelfStatus(Integer copy_id) throws SQLException;

    /**
     * 返回在架状态 true 在架
     * @param copy_id 目标id
     * @return 在架状态
     * @throws SQLException 有错误
     */
    boolean getOnShelfStatus(Integer copy_id) throws SQLException;

}
