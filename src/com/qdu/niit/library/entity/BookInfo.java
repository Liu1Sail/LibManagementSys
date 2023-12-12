package com.qdu.niit.library.entity;

import com.qdu.niit.library.Exception.ObjectHaveNoAttribute;

import java.util.Date;

public class BookInfo {
    Integer book_id;
    Integer copy_id;
    String title;
    String isbn;
    String author;
    String publisher;
    Date receipt_date;
    String genre;
    Integer book_quantity_visible;
    Integer book_quantity_hidden;
    Date acquisition_date;
    Boolean on_shelf_status;
    String book_location;
    Boolean is_visible;
    String room;
    String shelf_id;
    String compartment_id;
    String inner_number;


    /**
     * //插入copy的生成
     * @param title
     * @param isbn
     * @param author
     * @param publisher
     * @param genre
     * @param receipt_date
     * @param acquisition_date
     * @param book_location
     * @param room
     * @param is_visible
     */
    public BookInfo(String title, String isbn, String author,
                    String publisher, String genre,
                    Date receipt_date, Date acquisition_date,
                    String book_location,
                    String room, Boolean is_visible) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.receipt_date = receipt_date;
        this.genre = genre;
        this.acquisition_date = acquisition_date;
        this.book_location = book_location;
        this.is_visible = is_visible;
        this.room = room;
        String[] parts = book_location.split("-");
        shelf_id = parts[0];
        compartment_id = parts[1];
        inner_number = parts[2];
        on_shelf_status = true;
        book_quantity_visible = 0;
        book_quantity_hidden = 0;
        book_id = -1;
        copy_id = -1;
    }

    /**
     * 生成返回的Book
     * @param book_id
     * @param title
     * @param isbn
     * @param author
     * @param publisher
     * @param genre
     * @param receipt_date
     * @param book_location
     */
    public BookInfo(Integer book_id,String title, String isbn, String author,
                    String publisher, String genre,
                    Date receipt_date,
                    String book_location,
                    Integer book_quantity_visible,
                    Integer book_quantity_hidden   ) {   //插入copy的生成
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.receipt_date = receipt_date;
        this.genre = genre;
        String[] parts = book_location.split("-");
        shelf_id = parts[0];
        compartment_id = parts[1];
        inner_number = parts[2];
        on_shelf_status = true;
        this.book_quantity_visible = book_quantity_visible;
        this.book_quantity_hidden = book_quantity_hidden;
        this.book_id = book_id;
        copy_id = -1;
    }

    /**
     * 生成返回的copy
     * @param book_id
     * @param copy_id
     * @param title
     * @param isbn
     * @param author
     * @param publisher
     * @param receipt_date
     * @param genre
     * @param acquisition_date
     * @param room
     * @param book_location
     * @param on_shelf_status
     * @param is_visible
     */
    public BookInfo(Integer book_id, Integer copy_id, String title,
                    String isbn, String author,
                    String publisher, Date receipt_date, String genre,
                    Date acquisition_date, String room,
                    String book_location, Boolean on_shelf_status,
                    Boolean is_visible) {   //插入copy的生成
        this.book_id = book_id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.receipt_date = receipt_date;
        this.genre = genre;
        this.acquisition_date = acquisition_date;
        this.book_location = book_location;
        this.is_visible = is_visible;
        this.room = room;
        String[] parts = book_location.split("-");
        shelf_id = parts[0];
        compartment_id = parts[1];
        inner_number = parts[2];
        this.on_shelf_status = on_shelf_status;
        book_quantity_visible = 0;
        book_quantity_hidden = 0;
        book_id = -1;
        this.copy_id = copy_id;
    }


    public void setIsBook(){
        copy_id = -1;
    }
    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getCopy_id() {
        return copy_id;
    }

    public void setCopy_id(Integer copy_id) {
        this.copy_id = copy_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(Date receipt_date) {
        this.receipt_date = receipt_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getBook_quantity_visible() throws ObjectHaveNoAttribute {
        if(book_id != -1){
            return book_quantity_visible;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public void setBook_quantity_visible(Integer book_quantity_visible) throws ObjectHaveNoAttribute {
        if(book_id != -1){
            this.book_quantity_visible = book_quantity_visible;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }

    }

    public Integer getBook_quantity_hidden() throws ObjectHaveNoAttribute {
        if(book_id != -1){
            return book_quantity_hidden;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public void setBook_quantity_hidden(Integer book_quantity_hidden) throws ObjectHaveNoAttribute {
        if(book_id != -1){
            this.book_quantity_hidden = book_quantity_hidden;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }

    }

    public Date getAcquisition_date() throws ObjectHaveNoAttribute {
        if (copy_id != -1) {
            return acquisition_date;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }


    public void setAcquisition_date(Date acquisition_date) throws ObjectHaveNoAttribute {
        if (copy_id != -1) {
            this.acquisition_date = acquisition_date;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }

    }

    public Boolean getOn_shelf_status() throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            return on_shelf_status;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }

    }

    public void setOn_shelf_status(Boolean on_shelf_status) throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            this.on_shelf_status = on_shelf_status;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public String getBook_location() {
        return book_location;
    }

    public void setBook_location(String book_location) {
        this.book_location = book_location;
    }

    public Boolean getIs_visible() throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            return is_visible;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public void setIs_visible(Boolean is_visible) throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            this.is_visible = is_visible;

        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public String getRoom() throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            return room;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }
    }

    public void setRoom(String room) throws ObjectHaveNoAttribute {
        if(copy_id != -1){
            this.room = room;
        }
        else {
            throw new ObjectHaveNoAttribute();
        }

    }

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getCompartment_id() {
        return compartment_id;
    }

    public void setCompartment_id(String compartment_id) {
        this.compartment_id = compartment_id;
    }

    public String getInner_number() {
        return inner_number;
    }

    public void setInner_number(String inner_number) {
        this.inner_number = inner_number;
    }
}
