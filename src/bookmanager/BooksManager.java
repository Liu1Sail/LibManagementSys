package bookmanager;

import java.net.ConnectException;
import java.sql.SQLException;

public class BooksManager extends BOOK_MANAGER {
    public static final String tableName = "Books";
    public static final int tableId = 1;

    public BooksManager() throws SQLException, ConnectException {
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
