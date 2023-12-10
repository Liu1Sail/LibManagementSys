package bookmanager;

import java.util.Date;

public class Books extends BOOK{
    Integer book_id;
    String title;
    String isbn;
    String author;
    String publisher;
    Date receipt_date;
    String genre;
    Integer book_quantity_visible;
    Integer book_quantity_hidden;

    public Books(Integer bookID, String title, String isbn,
                 String author, String publisher, Date receiptDate,
                 String genre, Integer bookQuantity_Visible,
                 Integer bookQuantity_Hidden)
    {
        book_id = bookID;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        receipt_date = receiptDate;
        this.genre = genre;
        book_quantity_visible = bookQuantity_Visible;
        book_quantity_hidden = bookQuantity_Hidden;
    }
    public Books(String title, String isbn,
                 String author, String publisher, Date receiptDate,
                 String genre, Integer bookQuantity_Visible,
                 Integer bookQuantity_Hidden)
    {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        receipt_date = receiptDate;
        this.genre = genre;
        book_quantity_visible = bookQuantity_Visible;
        book_quantity_hidden = bookQuantity_Hidden;
    }

    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO Books(  title,
                                isbn,
                                author,
                                publisher,
                                receipt_date,
                                genre,
                                book_quantity_visible,
                                book_quantity_hidden
                                ) VALUES
                                (?,?,?,?,?,?,?,?);"""
            ;

    public String getInsertWithoutKeyStatement(){
        return INSERT_WITHOUT_KEY;
    }

}
