package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookCopy;

import java.sql.SQLException;

public interface BooksSQLDao {
    /**
     * 如果返回值是-1则不存在该书
     * @return book_id
     */
    int getIfBookIsExistByISBN(String isbn) throws SQLException;

    Integer insert(Book element) throws SQLException;

    void INCQuantityOfVisible(Integer book_id) throws SQLException;
    void INCQuantityOfNotVisible(Integer book_id) throws SQLException;

    void DECQuantityOfVisible(Integer book_id) throws SQLException;
    void DECQuantityOfNotVisible(Integer book_id) throws SQLException;
    boolean isEmpty(Integer book_id) throws SQLException;
    void delete(Integer book_id) throws SQLException;
}
