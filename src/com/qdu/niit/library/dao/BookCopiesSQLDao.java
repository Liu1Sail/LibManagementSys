package com.qdu.niit.library.dao;

import com.qdu.niit.library.Exception.objectHaveNoAttribute;
import com.qdu.niit.library.entity.BookCopy;
import com.qdu.niit.library.entity.BookInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookCopiesSQLDao {

    /**
     * 返回主键，向BookCopies中插入
     * 在insert函数中已经初始化BookCopy
     */
    Integer insertToBookCopies(BookCopy element) throws SQLException;
    void changeOnShelfStatus(Integer book_id) throws SQLException;
}
