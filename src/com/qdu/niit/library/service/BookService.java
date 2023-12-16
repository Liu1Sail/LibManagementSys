package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.exception.ObjectHaveNoAttribute;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface BookService {
    /**
     * 添加书本
     * @param in
     * @throws SQLException
     * @throws InstantiationException
     * @throws ObjectHaveNoAttribute
     */
    void Bookstorage(ArrayList<BookInfo> in)throws SQLException, InstantiationException, ObjectHaveNoAttribute;

    /**
     * 删除书本
     * @param copy_ids
     * @throws SQLException
     */
    void Bookoutbound(ArrayList<Integer> copy_ids) throws SQLException;

    /**
     * 根据CopyID查找
     * @param copy_id
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByCopyID(Integer copy_id) throws SQLException;

    /**
     * 根据date查找
     * @param date
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByDate(Date date) throws SQLException;

    /**
     * 根据book_id查找
     * @param book_id
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByBookID(Integer book_id) throws SQLException;

    /**
     * 根据author查找
     * @param author
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByAuthor(String author) throws SQLException;

    /**
     * 根据title查找
     * @param title
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByTitle(String title) throws SQLException;

    /**
     * 根据title，author查找
     * @param author
     * @param title
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> getBookInfoByAuthorAndTitle(String author, String title) throws SQLException;

    void borrowingBook(Integer copy_id, LocalDateTime end_time) throws SQLException;
    void backBook(Integer copy_id)throws SQLException;
    void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException;

    void updateBookLocationByCopyID(Integer copy_id, String location) throws SQLException;  //需要回滚

    void updateAuthorByBookID(Integer book_id, String author) throws SQLException;

    void updatePublisherByBookID(Integer book_id, String publisher) throws SQLException;

    void updateReceiptDateByBookID(Integer book_id, Date date) throws SQLException;

    void updateGenreByBookID(Integer book_id, String genre) throws SQLException;
}
