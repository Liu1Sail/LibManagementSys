package com.qdu.niit.library.entity;

import java.util.Date;

public class Book {
    Integer book_id;
    String title;
    String isbn;
    String author;
    String publisher;
    Date receipt_date;
    String genre;

    public Book(Integer book_id, String title,
                String isbn, String author,
                String publisher, Date receipt_date,
                String genre, Integer book_quantity_visible,
                Integer book_quantity_hidden, Integer total) {
        this.book_id = book_id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.receipt_date = receipt_date;
        this.genre = genre;
        this.book_quantity_visible = book_quantity_visible;
        this.book_quantity_hidden = book_quantity_hidden;
        this.total = total;
    }

    Integer book_quantity_visible;
    Integer book_quantity_hidden;
    Integer total;
    public Integer getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getReceipt_date() {
        return receipt_date;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getBook_quantity_visible() {
        return book_quantity_visible;
    }

    public Integer getBook_quantity_hidden() {
        return book_quantity_hidden;
    }

    public Book(Integer bookID, String title, String isbn,
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
    public Book(String title, String isbn,
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

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Book(BookInfo bookInfo){
        this.title = bookInfo.title;
        this.isbn = bookInfo.isbn;
        this.author = bookInfo.author;
        this.publisher = bookInfo.publisher;
        receipt_date = bookInfo.receipt_date;
        this.genre = bookInfo.genre;
        book_quantity_visible = 0;
        book_quantity_hidden = 0;
    }


}
