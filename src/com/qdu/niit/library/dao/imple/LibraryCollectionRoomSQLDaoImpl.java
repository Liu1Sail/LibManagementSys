package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.abstractdao.BOOK_MANAGER;
import com.qdu.niit.library.dao.LibraryCollectionRoomSQLDao;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.net.ConnectException;
import java.sql.SQLException;

public class LibraryCollectionRoomSQLDaoImpl extends BOOK_MANAGER implements LibraryCollectionRoomSQLDao {
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
    public static final String DELETE_BY_COPY_ID_STATEMENT = """
            DELETE FROM LibraryCollectionRoom
            WHERE copy_id = ?;"""
            ;
    public static String getDeleteByCopyIdStatement(){
        return DELETE_BY_COPY_ID_STATEMENT;
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
    public void delete(Integer book_id) throws SQLException {
        executeUpdate(getDeleteByCopyIdStatement(),book_id);
    }
    public LibraryCollectionRoomSQLDaoImpl() throws SQLException, ConnectException {
        super();
    }

    public String getTableName() {
        return tableName;
    }

    public int getTableId() {
        return tableId;
    }

}
