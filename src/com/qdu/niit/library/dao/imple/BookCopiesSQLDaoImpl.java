package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.AbstractDao.BOOK_MANAGER;
import com.qdu.niit.library.dao.BookCopiesSQLDao;
import com.qdu.niit.library.entity.BookCopy;

import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookCopiesSQLDaoImpl extends BOOK_MANAGER implements BookCopiesSQLDao {

    public static final String tableName = "BookCopies";
    public static final int tableId = 2;
    protected String getCreateTableStatement(){
        return TABLE_CREATE_BOOK_COPIES_STATEMENT;
    }
    public static final String TABLE_CREATE_BOOK_COPIES_STATEMENT = """
                CREATE TABLE IF NOT EXISTS BookCopies (
                copy_id INT AUTO_INCREMENT PRIMARY KEY,
                book_id INT,
                acquisition_date DATE,
                on_shelf_status BOOLEAN DEFAULT TRUE,
                book_location VARCHAR(50) UNIQUE,
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
    public void changeOnShelfStatus(Integer book_id) throws SQLException {
        executeUpdate(getChangeOnShelfStatusStatement(),book_id);
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
        executeUpdate(getDeleteByCopyIdStatement(),copy_id);
    }

    @Override
    public Integer insertToBookCopies(BookCopy element) throws SQLException {
        ArrayList<Object[]> theKeys = null;
        if (element == null){
            return -1;
        }
        theKeys = (
                executeUpdateAndGetGeneratedKeys(
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
