package bookmanager;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookCopiesSQLDaoImpl extends BOOK_MANAGER {
    public static final String tableName = "BookCopies";
    public static final int tableId = 2;
    public final String SELECT_BY_BOOK_ID = """
            SELECT * FROM BookCopies
            WHERE book_id = ?;
            """;
    public String getSelectByBookIDStatement(){
        return SELECT_BY_BOOK_ID;
    }
    /*
    返回值
    title
    isbn
    author
    publisher
    receipt_date
    genre
    copy_id
    acquisition_date
    on_shelf_status
    book_location
    is_visible  根据其判断是否可显示
     */
    public ArrayList<Object> selectByBookID(){
        ResultSet resultSet;

    }
    @Override
    public ArrayList<Object> insert(ArrayList<?> insertArrayOutGet) throws SQLException {
        //构建语句
        ArrayList<Object> theKeys = new ArrayList<>();

        if (insertArrayOutGet != null) {
            ArrayList<BookCopies> insertArray = castToBookCopiesArrayList(insertArrayOutGet);
            for (BookCopies element : insertArray) {
                 theKeys.add(
                         executeUpdateAndGetGeneratedKeys(
                                 element.getInsertWithoutKeyStatement(),
                                 element.book_id,
                                 element.acquisition_date,
                                 element.on_shelf_status,
                                 element.book_location,
                                 element.is_visible
                         )
                 );
            }
        }


        return theKeys;
    }


    @Override
    public int deleteByBookID(ArrayList<Integer> theKeyWantDelete) {
        return -1;
    }


    public BookCopiesSQLDaoImpl() throws SQLException, ConnectException {
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
