package bookmanager;

import utils.SqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class  BOOK_MANAGER {
    Connection connection;
    PreparedStatement statement = null;
    public static final String TABLE_CREATE_BOOKS_STATEMENT = """
            CREATE TABLE if NOT EXISTS(
             BookID INT PRIMARY KEY,
             Title VARCHAR(50) NOT NULL,
             ISBN VARCHAR(13) UNIQUE,
             Author VARCHAR(50) NOT NULL,
             Publisher VARCHAR(50) NOT NULL,
             ReceiptDate DATE NOT NULL,
             Genre VARCHAR(50) NOT NULL,
             BookQuantity_Visible INT NOT NULL,
             BookQuantity_Hidden INT NOT NULL
            );""";
    public static final String TABLE_CREATE_BOOK_COPIES_STATEMENT = """
                CREATE TABLE IF NOT EXISTS BookCopies (
                CopyID INT PRIMARY KEY,
                BookID INT,
                CopyNumber VARCHAR(50) NOT NULL,
                AcquisitionDate DATE,
                On_shelfStatus BOOLEAN,
                BookLocation VARCHAR(50),
                IsVisible BOOLEAN,
                FOREIGN KEY (BookID) REFERENCES Books(BookID)
            );""";

    public static final String TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT = """
                CREATE TABLE IF NOT EXISTS LibraryCollectionRoom (
                CopyID INT PRIMARY KEY,
                Room VARCHAR(10) NOT NULL,
                Shelf_ID CHAR(2) NOT NULL,
                Compartment_ID CHAR(3),
                InnerNumber CHAR(5) DEFAULT 'TRUE'
            );""";

    public Connection getConnection(){
        Connection conn;
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/1" , "root" , "root");//配置数据库
        try {
            conn =  SqlConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
