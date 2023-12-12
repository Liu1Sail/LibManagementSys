package com.qdu.niit.gui;

import com.qdu.niit.library.Exception.objectHaveNoAttribute;
import com.qdu.niit.library.dao.imple.BookCopiesSQLDaoImpl;
import com.qdu.niit.library.dao.imple.BookRepositorySQLDaoImpl;
import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.dao.imple.BooksSQLDaoImpl;
import com.qdu.niit.library.entity.BookCopy;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.utils.SqlConfig;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookTest {


    public static void main(String[] args) throws SQLException, ConnectException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, objectHaveNoAttribute {
//        BooksSQLDaoImpl booksManager = new BooksSQLDaoImpl();
//        long timestamp = System.currentTimeMillis(); // 获取当前时间的时间戳
//        Date date = new Date(timestamp);
//        ArrayList<Book> books = new ArrayList<>();
//        ArrayList<Integer> book_ids = new ArrayList<>();
//        book_ids.add(1);
//        booksManager.deleteByBookID(book_ids);
//        Book book = new Book("C++","1111-23123","Apollo","青岛大学出版社",date,"计算机",10,1);
//        books.add(book);
//        book = new Book("C","1111-23124","Apollo","青岛大学出版社",date,"计算机",10,1);
//        books.add(book);
//        book = new Book("ASM","1111-23125","Apollo","青岛大学出版社",date,"计算机",10,1);
//        books.add(book);
//        booksManager.insert(books);

//        BookCopiesSQLDaoImpl booksCopiesManager = new BookCopiesSQLDaoImpl();
//        BookCopy bookCopy = new BookCopy()
        long timestamp = System.currentTimeMillis(); // 获取当前时间的时间戳
        Date date = new Date(timestamp);
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library" , "root" , "root");//配置数据库
        BookRepositorySQLDaoImpl bookRepositoryManager = new BookRepositorySQLDaoImpl();

        ArrayList<BookInfo> books = new ArrayList<>();
        BookInfo bookInfo;

        bookInfo = new BookInfo("C++","123-33-22","Apollo","青岛大学出版社","CS",date,date,"A-123-22","302",true);
        books.add(bookInfo);
        bookInfo = new BookInfo("C++","123-33-22","Apollo","青岛大学出版社","CS",date,date,"A-123-23","302",true);
        books.add(bookInfo);
        bookInfo = new BookInfo("C++","123-33-22","Apollo","青岛大学出版社","CS",date,date,"A-123-24","302",false);
        books.add(bookInfo);
        bookInfo = new BookInfo("ASM","111-33-23","Apollo","青岛大学出版社","CS",date,date,"A-123-25","302",false);
        books.add(bookInfo);

        bookRepositoryManager.insert(books);
    }

}
