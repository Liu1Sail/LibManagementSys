package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.BookRepositorySQLDao;
import com.qdu.niit.library.dao.ReturningSQLDao;
import com.qdu.niit.library.dao.imple.BookRepositorySQLDaoImpl;
import com.qdu.niit.library.dao.imple.ReturningSQLDaoImpl;
import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;
import com.qdu.niit.library.service.BookService;
import com.qdu.niit.library.service.BorrowingService;
import com.qdu.niit.library.service.ReturningService;
import com.qdu.niit.library.service.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BookServiceImpl implements BookService {
    @Override
    public void Bookstorage(ArrayList<BookInfo> in) throws SQLException{
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.insert(in);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.insert(in);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void Bookoutbound(ArrayList<Integer> copy_ids) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.delete(copy_ids);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.delete(copy_ids);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByCopyID(Integer copy_id) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByCopyID(copy_id);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByCopyID(copy_id);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<BookInfo>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<BookInfo>();
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByDate(Date date) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByDate(date);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByDate(date);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<BookInfo>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<BookInfo>();
        }
    }

    @Override
    public ArrayList<BookInfo> getBookInfoByBookID(Integer book_id) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByBookID(book_id);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByBookID(book_id);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<BookInfo>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<BookInfo>();
        }
    }

    @Override
    public ArrayList<Book> getBookInfoByAuthor(String author) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByAuthor(author);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByAuthor(author);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<Book>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<Book>();
        }
    }

    public ArrayList<Book> getBookByTitle(String title) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByTitle(title);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByTitle(title);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<Book>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<Book>();
        }
    }

    @Override
    public ArrayList<Book> getBookByAuthorAndTitle(String author, String title) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            return apollo.getBookByAuthorAndTitle(author,title);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                return apollo.getBookByAuthorAndTitle(author,title);
            }  catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<Book>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<Book>();
        }
    }

    //先执行刘依洋的函数，如果出错则停止，然后执行华庆的函数，如果出错，则从刘依洋处删除已经添加进去的记录

    @Override
    public ArrayList<BookInfo> borrowingBook(Integer copy_id, LocalDateTime end_time) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            if(!apollo.getOnShelfStatus(copy_id))
            {
                return null;
            }
            UserService fan = UserServiceImpl.getInstance();
            Borrowing in = new Borrowing(fan.getLocalUser().getUID(),copy_id, LocalDateTime.now(),end_time);
            BorrowingService lyy = new BorrowingServiceImpl();
            lyy.insert(in);
        }catch (SQLException e)
        {
            try {
                UserService fan = UserServiceImpl.getInstance();
                Borrowing in = new Borrowing(fan.getLocalUser().getUID(),copy_id, LocalDateTime.now(),end_time);
                BorrowingService lyy = new BorrowingServiceImpl();
                lyy.insert(in);
            }catch (SQLException e1)
            {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }

        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.changeOnShelfStatus(copy_id);
            return apollo.getBookByCopyID(copy_id);
        }  catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.changeOnShelfStatus(copy_id);
                return apollo.getBookByCopyID(copy_id);
            } catch (SQLException e1) {
                //执行回滚
                BorrowingService lyy = new BorrowingServiceImpl();
                lyy.deleteByBid(copy_id);
                //
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return new ArrayList<BookInfo>();
            }
        }
        catch (Exception e2)
        {
            return new ArrayList<BookInfo>();
        }
    }

     //先执行刘依洋的函数，如果出错则停止，然后执行华庆的函数，如果出错，则从刘依洋处删除已经添加进去的记录
    @Override
    public boolean backBook(Integer copy_id) throws SQLException {
        LocalDateTime use = LocalDateTime.now();
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            if(apollo.getOnShelfStatus(copy_id))
            {
                return false;
            }
            UserService fan = UserServiceImpl.getInstance();
            Returning in = new Returning(fan.getLocalUser().getUID(),copy_id,use);
            ReturningService lyy = new ReturningServiceImpl();
            lyy.insert(in);
        }catch (SQLException e)
        {
            try {
                UserService fan = UserServiceImpl.getInstance();
                Returning in = new Returning(fan.getLocalUser().getUID(),copy_id,LocalDateTime.now());
                ReturningSQLDao lyy = ReturningSQLDaoImpl.getInstance();
                lyy.insert(in);
            }catch (SQLException e1)
            {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }

        }
        catch (Exception e2)
        {

        }
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.changeOnShelfStatus(copy_id);
            return true;
        }  catch (SQLException e)
        {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.changeOnShelfStatus(copy_id);
                return true;
            }catch (SQLException e1)
            {
                //执行回滚
                ReturningSQLDao lyy = ReturningSQLDaoImpl.getInstance();
                lyy.deleteByTime(use);
                //
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {
                return false;
            }

        }
        catch (Exception e2)
        {
            return false;
        }
    }

    @Override
    public void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updateAcquisitionByCopyID(copy_id,date);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updateAcquisitionByCopyID(copy_id,date);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void updateBookLocationByCopyID(Integer copy_id, String location) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updateBookLocationByCopyID(copy_id,location);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updateBookLocationByCopyID(copy_id,location);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void updateAuthorByBookID(Integer book_id, String author) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updateAuthorByBookID(book_id,author);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updateAuthorByBookID(book_id,author);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void updatePublisherByBookID(Integer book_id, String publisher) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updatePublisherByBookID(book_id,publisher);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updatePublisherByBookID(book_id,publisher);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void updateReceiptDateByBookID(Integer book_id, Date date) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updateReceiptDateByBookID(book_id,date);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updateReceiptDateByBookID(book_id,date);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

    @Override
    public void updateGenreByBookID(Integer book_id, String genre) throws SQLException {
        try {
            BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
            apollo.updateGenreByBookID(book_id,genre);
        } catch (SQLException e) {
            try {
                BookRepositorySQLDao apollo = new BookRepositorySQLDaoImpl();
                apollo.updateGenreByBookID(book_id,genre);
            } catch (SQLException e1) {
                throw new SQLException(e1);
            }
            catch (Exception e2)
            {

            }
        }
        catch (Exception e2)
        {

        }
    }

}
