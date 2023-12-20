package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Book;
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
    void BookAdd(ArrayList<BookInfo> in)throws SQLException, InstantiationException, ObjectHaveNoAttribute;

    /**
     * 删除书本,不是借书，直接移除
     * @param copy_ids
     * @throws SQLException
     */
    void BookDelete(ArrayList<Integer> copy_ids) throws SQLException;

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
    ArrayList<Book> getBookByAuthor(String author) throws SQLException;

    /**
     * 根据title查找
     * @param title
     * @return
     * @throws SQLException
     */
    ArrayList<Book> getBookByTitle(String title) throws SQLException;

    /**
     * 根据title，author查找
     * @param author
     * @param title
     * @return
     * @throws SQLException
     */
    ArrayList<Book> getBookByAuthorAndTitle(String author, String title) throws SQLException;

    /**
     * 对于书本已经被借出的情况返回null，对于华庆函数报出非sql错误以及没有查找到这本书，则返回空ArrayList<BookInfo>
     * @param copy_id
     * @param end_time
     * @return
     * @throws SQLException
     */
    ArrayList<BookInfo> borrowingBook(Integer copy_id, LocalDateTime end_time) throws SQLException;

    /**
     *false代表华庆除sql以外的错误以及该本书以及在库的情况，true代表执行成功
     * @param copy_id
     * @return
     * @throws SQLException
     */
    boolean backBook(Integer copy_id)throws SQLException;
    void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException;

    void updateBookLocationByCopyID(Integer copy_id, String location) throws SQLException;  //需要回滚

    void updateAuthorByBookID(Integer book_id, String author) throws SQLException;

    void updatePublisherByBookID(Integer book_id, String publisher) throws SQLException;

    void updateReceiptDateByBookID(Integer book_id, Date date) throws SQLException;

    void updateGenreByBookID(Integer book_id, String genre) throws SQLException;
    ArrayList<BookInfo>getCopyIdByBookId(Integer BookId)throws SQLException;//
}
