package bookmanager;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksSQLDaoImpl extends BOOK_MANAGER {
    public static final String tableName = "Books";
    public static final int tableId = 1;

    public final String SELECT_BY_BOOK_ID = """
            SELECT * FROM Books
            WHERE book_id = ?;
            """;
    public String getSelectByBookIDStatement(){
        return SELECT_BY_BOOK_ID;
    }
    @Override
    public ArrayList<Object> insert(ArrayList<?> insertArrayOutGet) throws SQLException {
        ArrayList<Object> theKeys = new ArrayList<>();

        if (insertArrayOutGet != null) {
                ArrayList<Books> insertArray = castToBooksArrayList(insertArrayOutGet);
                for (Books element : insertArray) {
                    theKeys.add(
                            executeUpdateAndGetGeneratedKeys(
                                    element.getInsertWithoutKeyStatement(),
                                    element.title,
                                    element.isbn,
                                    element.author,
                                    element.publisher,
                                    element.receipt_date,
                                    element.genre,
                                    element.book_quantity_visible,
                                    element.book_quantity_hidden
                            )
                    );
                }
            }
        return theKeys ;
    }

    public BooksSQLDaoImpl() throws SQLException, ConnectException {
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
