package bookmanager;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryCollectionRoomSQLDaoImpl extends BOOK_MANAGER{
    public static final String tableName = "LibraryCollectionRoom";
    public static final int tableId = 3;
    public final String SELECT_BY_BOOK_ID = """
            SELECT * FROM BookCopies
            WHERE book_id = ?;
            """;
    public String getSelectByBookIDStatement(){
        return SELECT_BY_BOOK_ID;
    }
    @Override
    public ArrayList<Object> insert(ArrayList<?> insertArrayOutGet) throws SQLException {
        ArrayList<Object> theKeys = new ArrayList<>();

        if (insertArrayOutGet != null) {
            ArrayList<LibraryCollectionRoom> insertArray = castToLibraryCollectionRoomArrayList(insertArrayOutGet);
            for (LibraryCollectionRoom element : insertArray) {
                theKeys.add(
                        executeUpdateAndGetGeneratedKeys(
                                element.getInsertWithoutKeyStatement(),
                                element.room,
                                element.shelf_id,
                                element.compartment_id,
                                element.inner_number
                        )
                ) ;
            }
        }
        return theKeys;
    }
    @Override
    //没有此方法
    public int deleteByBookID(ArrayList<Integer> theKeyWantDelete) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        return -1;
    }
    public LibraryCollectionRoomSQLDaoImpl() throws SQLException, ConnectException {
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
