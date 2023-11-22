package bookmanager;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MANAGER {
    String getTableName();

    int getTableId();

    //别忘了在子类调用setTableName
    boolean createTable() throws ConnectException, SQLException;

    int insertIntoSomeLine(ArrayList<?> insertArrayOutGet) throws SQLException;

    @SuppressWarnings("unchecked")
    default ArrayList<Books> castToBooksArrayList(ArrayList<?> list) {
        return (ArrayList<Books>) list;
    }

    @SuppressWarnings("unchecked")
    default ArrayList<BookCopies> castToBookCopiesArrayList(ArrayList<?> list) {
        return (ArrayList<BookCopies>) list;
    }

    @SuppressWarnings("unchecked")
    default ArrayList<LibraryCollectionRoom> castToLibraryCollectionRoomArrayList(ArrayList<?> list) {
        return (ArrayList<LibraryCollectionRoom>) list;
    }

    //获得 Connection
    Connection getConnection();
}
