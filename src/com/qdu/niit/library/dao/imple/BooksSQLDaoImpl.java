package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.AbstractDao.BOOK_MANAGER;
import com.qdu.niit.library.dao.BooksSQLDao;
import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookCopy;

import javax.crypto.interfaces.PBEKey;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksSQLDaoImpl extends BOOK_MANAGER implements BooksSQLDao {
    public static final String tableName = "Books";
    public static final int tableId = 1;
    protected String getCreateTableStatement(){
        return TABLE_CREATE_BOOKS_STATEMENT;
    }
    private static final String TABLE_CREATE_BOOKS_STATEMENT = """
            CREATE TABLE if NOT EXISTS Books(
             book_id INT AUTO_INCREMENT PRIMARY KEY,
             title VARCHAR(50) NOT NULL,
             isbn VARCHAR(13) UNIQUE,
             author VARCHAR(50) NOT NULL,
             publisher VARCHAR(50) NOT NULL,
             receipt_date DATE NOT NULL,
             genre VARCHAR(50) NOT NULL,
             book_quantity_visible INT NOT NULL,
             book_quantity_hidden INT NOT NULL
            );""";
    private static final String INSERT_WITHOUT_KEY = """
            INSERT INTO Books(  title,
                                isbn,
                                author,
                                publisher,
                                receipt_date,
                                genre,
                                book_quantity_visible,
                                book_quantity_hidden
                                ) VALUES
                                (?,?,?,?,?,?,0,0);"""
            ;
    public String getInsertWithoutKeyStatement(){
        return INSERT_WITHOUT_KEY;
    }

    public BooksSQLDaoImpl() throws SQLException, ConnectException {
        super();
    }

    public String getTableName() {
        return tableName;
    }

    public int getTableId() {
        return tableId;
    }


    private final static String GET_BOOK_ID_BY_ISBN= """
                SELECT book_id FROM Books
                WHERE isbn = ?;
        """ ;
    public static String getGetBookIdByISBNStatement(){
        return GET_BOOK_ID_BY_ISBN;
    }
    @Override
    public int getIfBookIsExistByISBN(String isbn) throws SQLException {
        Object[] result;
        result = getOne(
                getGetBookIdByISBNStatement(),
                isbn
        );
        if(result == null){
            return -1;
        }
        return (Integer) result[0];
    }

    @Override
    public Integer insert(Book element) throws SQLException {
        ArrayList<Object[]> theKeys = null;
        if (element == null){
            return -1;
        }
        theKeys = (
                executeUpdateAndGetGeneratedKeys(
                        getInsertWithoutKeyStatement(),
                        element.getTitle(),
                        element.getIsbn(),
                        element.getAuthor(),
                        element.getPublisher(),
                        element.getReceipt_date(),
                        element.getGenre()
                )
        );
        assert theKeys != null;
        Object[] obj = theKeys.get(0);
        return (Integer) obj[0];
    }


    private static final String INC_QUANTITY_OF_VISIBLE_STATEMENT = """
                UPDATE Books
                            SET book_quantity_visible = book_quantity_visible + 1
                            WHERE book_id = ?;
            """;
    private static final String INC_QUANTITY_OF_HIDDEN_STATEMENT = """
                UPDATE Books
                            SET book_quantity_hidden = book_quantity_hidden + 1
                            WHERE book_id = ?;
            """;
    private static final String DEC_QUANTITY_OF_VISIBLE_STATEMENT = """
                UPDATE Books
                            SET book_quantity_visible = book_quantity_visible - 1
                            WHERE book_id = ?;
            """;
    private static final String DEC_QUANTITY_OF_HIDDEN_STATEMENT = """
                UPDATE Books
                            SET book_quantity_hidden = book_quantity_hidden - 1
                            WHERE book_id = ?;
            """;
    private static final String GET_BOOK_QUANTITY_STATEMENT = """
                SELECT book_quantity_visible,book_quantity_hidden FROM Books
                WHERE book_id = ?;
            """;

    public static String getGetBookQuantityStatement() {
        return GET_BOOK_QUANTITY_STATEMENT;
    }

    public static String getDecQuantityOfHiddenStatement() {
        return DEC_QUANTITY_OF_HIDDEN_STATEMENT;
    }

    public static String getIncQuantityOfVisibleStatement() {
        return INC_QUANTITY_OF_VISIBLE_STATEMENT;
    }

    public static String getDecQuantityOfVisibleStatement() {
        return DEC_QUANTITY_OF_VISIBLE_STATEMENT;
    }

    public static String getIncQuantityOfHiddenStatement() {
        return INC_QUANTITY_OF_HIDDEN_STATEMENT;
    }

    @Override
    public void INCQuantityOfVisible(Integer book_id) throws SQLException {
        executeUpdate(getIncQuantityOfVisibleStatement(),book_id);
    }

    @Override
    public void INCQuantityOfNotVisible(Integer book_id) throws SQLException {
        executeUpdate(getIncQuantityOfHiddenStatement(),book_id);
    }

    @Override
    public void DECQuantityOfVisible(Integer book_id) throws SQLException {
        executeUpdate(getDecQuantityOfVisibleStatement(),book_id);
    }

    @Override
    public void DECQuantityOfNotVisible(Integer book_id) throws SQLException {
        executeUpdate(getDecQuantityOfHiddenStatement());
    }

    @Override
    public boolean isEmpty(Integer book_id) throws SQLException {
        Object[] result = null;
        result = getOne(getGetBookQuantityStatement(),book_id);
        return (Integer) result[0] != 0 || (Integer) result[1] != 0;
    }

    private static final String DELETE_BY_BOOK_ID = """
            DELETE FROM Books
            WHERE book_id = ?;
            """;
    private static String getDeleteByBookIdStatement(){
        return DELETE_BY_BOOK_ID;
    }

    @Override
    public void delete(Integer book_id) throws SQLException {
        executeUpdate(getDeleteByBookIdStatement(),book_id);
    }

}
