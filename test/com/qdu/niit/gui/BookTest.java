package com.qdu.niit.gui;

import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.dao.imple.BookRepositorySQLDaoImpl;
import com.qdu.niit.library.utils.SqlConfig;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookTest {


    public static void main(String[] args) throws SQLException, ConnectException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, ObjectHaveNoAttribute {

        long timestamp = System.currentTimeMillis(); // 获取当前时间的时间戳
        Date date = new Date(timestamp);
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/qdu" , "root" , "root");//配置数据库
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
//         bookRepositoryManager.getBookByBookID(1);
//        ArrayList<BookInfo> bookss = bookRepositoryManager.getBookByCopyID(1);
//        ArrayList<Integer> copy_ids= new ArrayList<>();
//        copy_ids.add(1);
//        copy_ids.add(2);
//        copy_ids.add(3);
//        bookRepositoryManager.delete(copy_ids);
    }

}
