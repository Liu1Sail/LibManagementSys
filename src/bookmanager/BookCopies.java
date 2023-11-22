package bookmanager;

import java.util.Date;

public class BookCopies extends BOOK {
    public BookCopies(Integer copyID, Integer bookID, String copyNumber,
                      Date acquisitionDate, Boolean on_shelfStatus,
                      String bookLocation, Boolean isVisible)
    {
        CopyID = copyID;
        BookID = bookID;
        CopyNumber = copyNumber;
        AcquisitionDate = acquisitionDate;
        On_shelfStatus = on_shelfStatus;
        BookLocation = bookLocation;
        IsVisible = isVisible;
    }

    //无主键的构造函数
    public BookCopies(Integer bookID, String copyNumber,
                      Date acquisitionDate, Boolean on_shelfStatus,
                      String bookLocation, Boolean isVisible)
    {
        BookID = bookID;
        CopyNumber = copyNumber;
        AcquisitionDate = acquisitionDate;
        On_shelfStatus = on_shelfStatus;
        BookLocation = bookLocation;
        IsVisible = isVisible;
    }

    public static final String tableName = "BookCopies";
    Integer CopyID;
    Integer BookID;
    String CopyNumber;
    Date AcquisitionDate;
    Boolean On_shelfStatus;
    String BookLocation;
    Boolean IsVisible;


    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO Books(  BookID,
                                CopyNumber;
                                AcquisitionDate;
                                On_shelfStatus;
                                BookLocation;
                                IsVisible;
                                ) VALUES
                                (?,?,?,?,?,?);"""
            ;


    @Override
    public String getInsertWithoutKeyStatement() {
        return INSERT_WITHOUT_KEY;
    }

}
