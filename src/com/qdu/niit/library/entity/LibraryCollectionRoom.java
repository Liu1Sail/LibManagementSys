package com.qdu.niit.library.entity;


import com.qdu.niit.library.AbstractDao.BOOK;


public class LibraryCollectionRoom extends BOOK {

    Integer copy_id;
    String room;
    String shelf_id;
    String compartment_id;
    String inner_number;

    public Integer getCopy_id() {
        return copy_id;
    }

    public String getRoom() {
        return room;
    }

    public String getShelf_id() {
        return shelf_id;
    }

    public String getCompartment_id() {
        return compartment_id;
    }

    public String getInner_number() {
        return inner_number;
    }

    public LibraryCollectionRoom(Integer copyID, String room, String shelf_ID, String compartment_ID, String innerNumber) {
        this.copy_id = copyID;
        this.room = room;
        this.shelf_id = shelf_ID;
        this.compartment_id = compartment_ID;
        this.inner_number = innerNumber;
    }

    /**
     * 无主键的构造函数
     */
    public LibraryCollectionRoom(String room, String shelf_ID, String compartment_ID, String innerNumber) {
        this.room = room;
        this.shelf_id = shelf_ID;
        this.compartment_id = compartment_ID;
        this.inner_number = innerNumber;
    }
    public LibraryCollectionRoom(BookInfo bookInfo) {
        this.room = bookInfo.room;
        this.shelf_id = bookInfo.shelf_id;
        this.compartment_id = bookInfo.compartment_id;
        this.inner_number = bookInfo.inner_number;
    }

    public void setCopy_id(Integer copy_id) {
        this.copy_id = copy_id;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public void setCompartment_id(String compartment_id) {
        this.compartment_id = compartment_id;
    }

    public void setInner_number(String inner_number) {
        this.inner_number = inner_number;
    }
}
