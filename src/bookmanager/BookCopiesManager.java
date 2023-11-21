package bookmanager;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookCopiesManager extends BOOK_MANAGER{
    public static final String tableName = "BookCopies";
    public static final int tableId = 2;

    public BookCopiesManager() throws SQLException, ConnectException {
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
