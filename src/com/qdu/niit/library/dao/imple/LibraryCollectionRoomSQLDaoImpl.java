package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.abstractdao.BOOK_MANAGER;
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
                compartment_id CHAR(3) NOT NULL,
                inner_number CHAR(5) NOT NULL,
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
    private static final String UPDATE_LOCATION_BY_COPY_ID = """
            UPDATE LibraryCollectionRoom
            SET shelf_id = ?,compartment_id = ?,inner_number = ?
            WHERE copy_id = ?;
            """ ;

    public static String getUpdateLocationByCopyId() {
        return UPDATE_LOCATION_BY_COPY_ID;
    }
    void updateLocationByCopyId(     //需要事务
            Integer copy_id,
            String shelf_id,
            String compartment_id,
            String inner_number) throws SQLException {

        executeTransactionUpdate(shelf_id,compartment_id,inner_number,copy_id);
    }

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
        executeTransactionUpdate(
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
        executeTransactionUpdate(getDeleteByCopyIdStatement(),book_id);
    }
    private LibraryCollectionRoomSQLDaoImpl() throws SQLException {
        super();
    }

    public String getTableName() {
        return tableName;
    }

    public int getTableId() {
        return tableId;
    }

}
