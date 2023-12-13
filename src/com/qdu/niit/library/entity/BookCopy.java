package com.qdu.niit.library.entity;

import com.qdu.niit.library.abstractdao.BOOK;

import java.util.Date;

public class BookCopy extends BOOK {
    Integer copy_id;
    Integer book_id;
    Date acquisition_date;
    Boolean on_shelf_status;
    String book_location;
    Boolean is_visible;
    public BookCopy(Integer copyID, Integer bookID,
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
    public BookCopy(Integer bookID,
                    Date acquisitionDate, Boolean on_shelfStatus,
                    String bookLocation, Boolean isVisible)
    {
        book_id = bookID;
        acquisition_date = acquisitionDate;
        on_shelf_status = on_shelfStatus;
        book_location = bookLocation;
        is_visible = isVisible;
    }
    public BookCopy(BookInfo bookInfo)
    {
        acquisition_date = bookInfo.acquisition_date;
        on_shelf_status = bookInfo.on_shelf_status;
        book_location = bookInfo.book_location;
        is_visible = bookInfo.is_visible;
    }

    public void setCopy_id(Integer copy_id) {
        this.copy_id = copy_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public static final String tableName = "BookCopies";


    public Integer getCopy_id() {
        return copy_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public Date getAcquisition_date() {
        return acquisition_date;
    }

    public Boolean getOn_shelf_status() {
        return on_shelf_status;
    }

    public String getBook_location() {
        return book_location;
    }

    public Boolean getIs_visible() {
        return is_visible;
    }







}
