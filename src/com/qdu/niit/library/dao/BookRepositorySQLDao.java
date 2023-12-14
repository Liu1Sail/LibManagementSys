package com.qdu.niit.library.dao;

import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.entity.BookInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface BookRepositorySQLDao {
    void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException, InstantiationException, ObjectHaveNoAttribute;

    void delete(ArrayList<Integer> copy_ids) throws SQLException;

    ArrayList<BookInfo> getBookByCopyID(Integer copy_id) throws SQLException;

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
