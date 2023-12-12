package com.qdu.niit.library.dao;

import com.qdu.niit.library.GenericDao.MANAGER;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryCollectionRoomSQLDao extends MANAGER {
    void insert(LibraryCollectionRoom element) throws SQLException;
    void delete(Integer book_id) throws SQLException;
}
