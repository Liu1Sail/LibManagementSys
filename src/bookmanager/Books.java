package bookmanager;

import java.util.Date;

public class Books extends BOOK{
    Integer BookID;
    String Title;
    String ISBN;
    String Author;
    String Publisher;
    Date ReceiptDate;
    String Genre;
    Integer BookQuantity_Visible;
    Integer BookQuantity_Hidden;

    public Books(Integer bookID, String title, String ISBN,
                 String author, String publisher, Date receiptDate,
                 String genre, Integer bookQuantity_Visible,
                 Integer bookQuantity_Hidden)
    {
        BookID = bookID;
        Title = title;
        this.ISBN = ISBN;
        Author = author;
        Publisher = publisher;
        ReceiptDate = receiptDate;
        Genre = genre;
        BookQuantity_Visible = bookQuantity_Visible;
        BookQuantity_Hidden = bookQuantity_Hidden;
    }
    public Books(String title, String ISBN,
                 String author, String publisher, Date receiptDate,
                 String genre, Integer bookQuantity_Visible,
                 Integer bookQuantity_Hidden)
    {
        Title = title;
        this.ISBN = ISBN;
        Author = author;
        Publisher = publisher;
        ReceiptDate = receiptDate;
        Genre = genre;
        BookQuantity_Visible = bookQuantity_Visible;
        BookQuantity_Hidden = bookQuantity_Hidden;
    }

    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO Books(  Title,
                                ISBN,
                                Author,
                                Publisher,
                                ReceiptDate,
                                Genre,
                                BookQuantity_Visible,
                                BookQuantity_Hidden
                                ) VALUES
                                (?,?,?,?,?,?,?,?);"""
            ;

    public String getInsertWithoutKeyStatement(){
        return INSERT_WITHOUT_KEY;
    }

}
