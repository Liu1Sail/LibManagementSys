package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.AbstractDao.BOOK_MANAGER;
import com.qdu.niit.library.dao.LibraryCollectionRoomSQLDao;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryCollectionRoomSQLDaoImpl extends BOOK_MANAGER implements LibraryCollectionRoomSQLDao {
    private LibraryCollectionRoomSQLDaoImpl(int everything){
        super(1);
    }
    public static final String tableName = "LibraryCollectionRoom";
    public static final int tableId = 3;
    protected String getCreateTableStatement(){
        return TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT;
    }
    public static final String TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT = """
                CREATE TABLE IF NOT EXISTS LibraryCollectionRoom (
                copy_id INT PRIMARY KEY,
                room VARCHAR(10) NOT NULL,
                shelf_id CHAR(2) NOT NULL,
                compartment_id CHAR(3),
                inner_number CHAR(5),
                FOREIGN KEY (copy_id) REFERENCES BookCopies(copy_id)
            );""";
    public static final String INSERT_STATEMENT = """
            INSERT INTO LibraryCollectionRoom (
                                copy_id,
                                room,
                                shelf_id,
                                compartment_id,
                                inner_number
                                ) VALUES
                                (?,?,?,?,?);"""
            ;
    public String getInsertStatement(){
        return INSERT_STATEMENT;
    }
    public static final String DELETE_BY_COPY_ID = """
            DELETE FROM LibraryCollectionRoom WHERE copy_id = ? ;"""
            ;
    public static String getDeleteByCopyIdStatement(){
        return DELETE_BY_COPY_ID;
    }
    public static final String DELETE_BY_BOOK_ID = """
            DELETE FROM LibraryCollectionRoom WHERE book_id = ? ;"""
            ;
    public static String getDeleteByBookIdStatement(){
        return DELETE_BY_BOOK_ID;
    }
    public final String SELECT_BY_BOOK_ID = """
            SELECT * FROM LibraryCollectionRoom
            WHERE book_id = ?;
            """;
    public String getSelectByBookIDStatement(){
        return SELECT_BY_BOOK_ID;
    }
    @Override
    public void insert(LibraryCollectionRoom element) throws SQLException {
        executeUpdate(
                getInsertStatement(),
                element.getCopy_id(),
                element.getRoom(),
                element.getShelf_id(),
                element.getCompartment_id(),
                element.getInner_number()
        );
    }
    @Override
    //没有此方法
    public int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        return -1;
    }
    public LibraryCollectionRoomSQLDaoImpl() throws SQLException, ConnectException {
        super();
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public int getTableId() {
        return tableId;
    }
}
