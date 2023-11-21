package bookmanager;


import java.sql.PreparedStatement;





public class LibraryCollectionRoom extends BOOK {

    Integer CopyID;
    String Room;
    String Shelf_ID;
    String Compartment_ID;
    String InnerNumber;

    public LibraryCollectionRoom(Integer copyID, String room, String shelf_ID, String compartment_ID, String innerNumber) {
        CopyID = copyID;
        Room = room;
        Shelf_ID = shelf_ID;
        Compartment_ID = compartment_ID;
        InnerNumber = innerNumber;
    }

    /**
     * 无主键的构造函数
     */
    public LibraryCollectionRoom(String room, String shelf_ID, String compartment_ID, String innerNumber) {
        Room = room;
        Shelf_ID = shelf_ID;
        Compartment_ID = compartment_ID;
        InnerNumber = innerNumber;
    }

    public static final String INSERT_WITHOUT_KEY = """
            INSERT INTO Books(  Room
                                Shelf_ID
                                Compartment_ID
                                InnerNumber
                                ) VALUES
                                (?,?,?,?);"""
            ;
    @Override
    public String getInsertWithoutKeyStatement(){
        return INSERT_WITHOUT_KEY;
    }
}
