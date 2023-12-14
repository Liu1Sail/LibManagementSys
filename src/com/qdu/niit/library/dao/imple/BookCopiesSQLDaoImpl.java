package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.abstractdao.BOOK_MANAGER;
import com.qdu.niit.library.dao.BookCopiesSQLDao;
import com.qdu.niit.library.entity.BookCopy;

import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookCopiesSQLDaoImpl extends BOOK_MANAGER implements BookCopiesSQLDao {

    public static final String tableName = "BookCopies";
    public static final int tableId = 2;
    protected String getCreateTableStatement(){
        return TABLE_CREATE_BOOK_COPIES_STATEMENT;
    }
    public static final String UPDATE_ACQUISITION_BY_COPY_ID_STATEMENT = """
            UPDATE BookCopies
            SET acquisition_date = ?
            WHERE copy_id = ?;
            """;
    public String getUpdateAcquisitionByCopyIdStatement(){
        return  UPDATE_ACQUISITION_BY_COPY_ID_STATEMENT;
    }
    void updateAcquisitionByCopyID(Integer copy_id, Date date) throws SQLException {
        executeUpdate(getUpdateAcquisitionByCopyIdStatement(),date,copy_id);
    }
    public static final String UPDATE_BOOK_LOCATION_BY_COPY_ID_STATEMENT = """
            UPDATE BookCopies
            SET book_location = ?
            WHERE copy_id = ?;
            """;
    public String getUpdateBookLocationByCopyIdStatement(){
        return  UPDATE_ACQUISITION_BY_COPY_ID_STATEMENT;
    }

    /**
     * 需要连锁修改
     * @param copy_id
     * @param location
     * @throws SQLException
     */
    void updateBookLocationByCopyIDInCopies(Integer copy_id, String location) throws SQLException {
        executeTransactionUpdate(getUpdateBookLocationByCopyIdStatement(),location,copy_id);
    }

    public static final String TABLE_CREATE_BOOK_COPIES_STATEMENT = """
                CREATE TABLE IF NOT EXISTS BookCopies (
                copy_id INT AUTO_INCREMENT PRIMARY KEY,
                book_id INT UNIQUE NOT NULL,
                acquisition_date DATE NOT NULL,
                on_shelf_status BOOLEAN DEFAULT TRUE,
                book_location VARCHAR(50) UNIQUE NOT NULL,
                is_visible BOOLEAN DEFAULT TRUE,
                FOREIGN KEY (book_id) REFERENCES Books(book_id)
            );""";

    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO BookCopies(  book_id,
                                acquisition_date,
                                on_shelf_status,
                                book_location,
                                is_visible
                                ) VALUES
                                (?,?,?,?,?);"""
            ;
    public String getInsertWithoutKeyStatement() {
        return INSERT_WITHOUT_KEY;
    }

    private static final String CHANGE_ON_SHELF_STATUS_STATEMENT = """
            UPDATE BookCopies
            SET on_shelf_status = NOT on_shelf_status
            WHERE copy_id = ?;
            """;

    public static String getChangeOnShelfStatusStatement() {
        return CHANGE_ON_SHELF_STATUS_STATEMENT;
    }

    @Override
    public void changeOnShelfStatus(Integer copy_id) throws SQLException {
        executeUpdate(getChangeOnShelfStatusStatement(),copy_id);
    }

    public static final String GET_IS_VISIBLE_STATEMENT = """
            SELECT is_visible FROM BookCopies
            WHERE copy_id = ?;
            """;
    public String getGetIsVisibleStatement(){
        return GET_IS_VISIBLE_STATEMENT;
    }
    @Override
    public boolean getIsVisibleByCopyID(Integer copy_id) throws SQLException {
        Object[] result = null;
        result = getOne(getGetIsVisibleStatement(),copy_id);
        return (boolean) result[0];
    }

    public static final String DELETE_BY_COPY_ID = """
            DELETE FROM BookCopies
            WHERE copy_id = ?;
            """;
    public static String getDeleteByCopyIdStatement(){
        return DELETE_BY_COPY_ID;
    }

    @Override
    public void delete(Integer copy_id) throws SQLException {
        executeTransactionUpdate(getDeleteByCopyIdStatement(),copy_id);
    }

    @Override
    public Integer insertToBookCopies(BookCopy element) throws SQLException {
        ArrayList<Object[]> theKeys = null;
        if (element == null){
            return -1;
        }
        theKeys = (
                executeTransactionUpdateAndGetKeys(
                        getInsertWithoutKeyStatement(),
                        element.getBook_id(),
                        element.getAcquisition_date(),
                        element.getOn_shelf_status(),
                        element.getBook_location(),
                        element.getIs_visible()
                )
        );
        assert theKeys != null;
        Object[] obj = theKeys.get(0);
        BigInteger bigInteger = (BigInteger) obj[0];
        return bigInteger.intValue();
    }



    public BookCopiesSQLDaoImpl() throws SQLException, ConnectException {
        super();
    }
    public String getTableName() {
        return tableName;
    }
    public int getTableId() {
        return tableId;
    }


}
