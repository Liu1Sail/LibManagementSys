package bookmanager;

import com.qdu.niit.library.dao.BaseSQLDao;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public interface MANAGER  {
    String getTableName();

    int getTableId();

    //别忘了在子类调用setTableName

    boolean createTable() throws ConnectException, SQLException;
    ArrayList<Object> insert(ArrayList<?> insertArrayOutGet) throws SQLException;
    int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    int deleteByCopyID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

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
