package bookmanager;


import java.sql.PreparedStatement;





public class LibraryCollectionRoom extends BOOK {

    Integer copy_id;
    String room;
    String shelf_id;
    String compartment_id;
    String inner_number;

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

    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO LibraryCollectionRoom (
                                room
                                shelf_id
                                compartment_id
                                inner_number
                                ) VALUES
                                (?,?,?,?);"""
            ;
    @Override
    public String getInsertWithoutKeyStatement(){
        return INSERT_WITHOUT_KEY;
    }
}
