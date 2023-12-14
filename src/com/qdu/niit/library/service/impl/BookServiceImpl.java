package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.BookRepositorySQLDao;
import com.qdu.niit.library.dao.ReturningSQLDao;
import com.qdu.niit.library.dao.imple.BookRepositorySQLDaoImpl;
import com.qdu.niit.library.dao.imple.ReturningSQLDaoImpl;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;
import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.service.BookService;
import com.qdu.niit.library.service.BorrowingService;
import com.qdu.niit.library.service.ReturningService;
import com.qdu.niit.library.service.UserService;

import java.net.ConnectException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BookServiceImpl implements BookService {
    @Override
    public void Bookstorage(ArrayList<BookInfo> in) throws SQLException, InstantiationException, ObjectHaveNoAttribute {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            use.insert(in);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                use.insert(in);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public void Bookoutbound(ArrayList<Integer> copy_ids) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            use.delete(copy_ids);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                use.delete(copy_ids);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByCopyID(Integer copy_id) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByCopyID(copy_id);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByCopyID(copy_id);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByDate(Date date) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByDate(date);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByDate(date);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByBookID(Integer book_id) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByBookID(book_id);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByBookID(book_id);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByAuthor(String author) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByAuthor(author);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByAuthor(author);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByTitle(String title) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByTitle(title);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByTitle(title);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByAuthorAndTitle(String author, String title) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            return use.getBookByAuthorAndTitle(author,title);
        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                return use.getBookByAuthorAndTitle(author,title);
            }  catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public void borrowingBook(Integer copy_id, LocalDateTime end_time) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            use.changeOnShelfStatus(copy_id);

        }  catch (Exception e) {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                use.changeOnShelfStatus(copy_id);
            } catch (Exception e1) {
                throw new SQLException(e1);
            }
        }
        try {
            UserService fan = UserServiceImpl.getInstance();
            Borrowing in = new Borrowing(fan.getLocalUser().getUID(),copy_id, LocalDateTime.now(),end_time);
            BorrowingService lyy = new BorrowingServiceImpl();
            lyy.insert(in);
        }catch (Exception e)
        {
            try {
                UserService fan = UserServiceImpl.getInstance();
                Borrowing in = new Borrowing(fan.getLocalUser().getUID(),copy_id, LocalDateTime.now(),end_time);
                BorrowingService lyy = new BorrowingServiceImpl();
                lyy.insert(in);
            }catch (Exception e1)
            {
                throw new SQLException(e1);
            }
        }
    }

    @Override
    public void backBook(Integer copy_id) throws SQLException {
        try {
            BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
            use.changeOnShelfStatus(copy_id);
        }  catch (Exception e)
        {
            try {
                BookRepositorySQLDao use = new BookRepositorySQLDaoImpl();
                use.changeOnShelfStatus(copy_id);
            }catch (Exception e1)
            {
                throw new SQLException(e1);
            }

        }
        try {
            UserService fan = UserServiceImpl.getInstance();
            Returning in = new Returning(fan.getLocalUser().getUID(),copy_id,LocalDateTime.now());
            ReturningService lyy = new ReturningServiceImpl();
            lyy.insert(in);
        }catch (Exception e)
        {
            try {
                UserService fan = UserServiceImpl.getInstance();
                Returning in = new Returning(fan.getLocalUser().getUID(),copy_id,LocalDateTime.now());
                ReturningSQLDao lyy = new ReturningSQLDaoImpl();
                lyy.insert(in);
            }catch (Exception e1)
            {
                throw new SQLException(e1);
            }

        }

    }

    @Override
    public void changeBook() {

    }
}