package bookmanager;

import java.util.Date;

public class BookCopies extends BOOK {
    public BookCopies(Integer copyID, Integer bookID,
                      Date acquisitionDate, Boolean on_shelfStatus,
                      String bookLocation, Boolean isVisible)
    {
        copy_id = copyID;
        book_id = bookID;
        acquisition_date = acquisitionDate;
        on_shelf_status = on_shelfStatus;
        book_location = bookLocation;
        is_visible = isVisible;
    }

    //无主键的构造函数
    public BookCopies(Integer bookID,
                      Date acquisitionDate, Boolean on_shelfStatus,
                      String bookLocation, Boolean isVisible)
    {
        book_id = bookID;
        acquisition_date = acquisitionDate;
        on_shelf_status = on_shelfStatus;
        book_location = bookLocation;
        is_visible = isVisible;
    }

    public static final String tableName = "BookCopies";
    Integer copy_id;
    Integer book_id;
    Date acquisition_date;
    Boolean on_shelf_status;
    String book_location;
    Boolean is_visible;


    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO BookCopies(  book_id,
                                acquisition_date;
                                on_shelf_status;
                                book_location;
                                is_visible;
                                ) VALUES
                                (?,?,?,?,?);"""
            ;
    public static final String DELETE_BY_COPY_ID = """
            DELETE FROM BookCopies WHERE copy_id = ? ;"""
            ;


    @Override
    public String getInsertWithoutKeyStatement() {
        return INSERT_WITHOUT_KEY;
    }
    public static String getDeleteByCopyIdStatement(){
        return DELETE_BY_COPY_ID;
    }

}
