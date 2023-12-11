package com.qdu.niit.library.dao;

import com.qdu.niit.library.Exception.objectHaveNoAttribute;
import com.qdu.niit.library.entity.BookInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookRepositorySQLDao {
    public void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, objectHaveNoAttribute;
}
