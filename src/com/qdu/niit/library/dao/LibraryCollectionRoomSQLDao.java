package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.sql.SQLException;

public interface LibraryCollectionRoomSQLDao  {
    void insert(LibraryCollectionRoom element) throws SQLException;
    void delete(Integer book_id) throws SQLException;
}
