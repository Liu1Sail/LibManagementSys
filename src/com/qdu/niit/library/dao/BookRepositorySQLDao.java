package com.qdu.niit.library.dao;

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
     * @throws ObjectHaveNoAttribute   自定义异常，指示对象缺少必需的属性。
     * @throws RuntimeException        如果在事务执行过程中发生意外错误，导致回滚。
     */
    void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException, ObjectHaveNoAttribute;

    void delete(ArrayList<Integer> copy_ids) throws SQLException;

    ArrayList<BookInfo> getBookByCopyID(Integer copy_id) throws SQLException;

    void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException;

    void updateBookLocationByCopyID(Integer copy_id, String location) throws SQLException;  //需要回滚

    void updateAuthorByBookID(Integer book_id, String author) throws SQLException;

    void updatePublisherByBookID(Integer book_id, String publisher) throws SQLException;

    void updateReceiptDateByBookID(Integer book_id, Date date) throws SQLException;

    void updateGenreByBookID(Integer book_id, String genre) throws SQLException;

    ArrayList<BookInfo> getBookByDate(Date date) throws SQLException;

    ArrayList<BookInfo> getBookByBookID(Integer book_id) throws SQLException;

    ArrayList<BookInfo> getBookByAuthor(String author) throws SQLException;

    ArrayList<BookInfo> getBookByTitle(String title) throws SQLException;

    ArrayList<BookInfo> getBookByAuthorAndTitle(String author, String title) throws SQLException;

    void changeOnShelfStatus(Integer copy_id) throws SQLException;

    default ArrayList<BookInfo> getBookInfos(ArrayList<Object[]> gotResult, ArrayList<BookInfo> result) {
        BookInfo bookInfo;
        for (Object[] obj : gotResult) {
            bookInfo = new BookInfo((Integer) obj[0], (Integer) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (Date) obj[6], (String) obj[7], (Date) obj[8], (String) obj[9], (String) obj[10], (Boolean) obj[11], (Boolean) obj[12]);
            result.add(bookInfo);
        }
        return result;
    }
}
