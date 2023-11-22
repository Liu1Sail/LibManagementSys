package bookmanager;

import java.net.ConnectException;
import java.sql.SQLException;

public class LibraryCollectionRoomManager extends BOOK_MANAGER{
    public static final String tableName = "LibraryCollectionRoom";
    public static final int tableId = 3;

    public LibraryCollectionRoomManager() throws SQLException, ConnectException {
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
