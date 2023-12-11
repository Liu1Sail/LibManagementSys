package com.qdu.niit.library.GenericDao;

import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookCopy;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MANAGER  {
    String getTableName();

    int getTableId();

    //别忘了在子类调用setTableName

    void createTable() throws ConnectException, SQLException;
//    int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
//    int deleteByCopyID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;



    //获得 Connection
    Connection getConnection();
}
