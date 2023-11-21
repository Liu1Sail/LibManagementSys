package bookmanager;

import utils.SqlConfig;
import utils.SqlUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

abstract class  BOOK_MANAGER implements MANAGER {
    Connection connection = null;
    PreparedStatement statement = null;
    String tableName = null;  //需要初始化
    String tableId = null;


    //别忘了在子类调用setTableName
    @Override
    public boolean createTable() throws ConnectException, SQLException {
        //连接是否丢失
        if(connection == null){
            throw new ConnectException();
        }
        String createTableStatement = null;

        //选择要建表的语句
        switch (getTableId()) {
            case 1 -> createTableStatement = TABLE_CREATE_BOOKS_STATEMENT;
            case 2 -> createTableStatement = TABLE_CREATE_BOOK_COPIES_STATEMENT;
            case 3 -> createTableStatement = TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT;
        }
        statement = connection.prepareStatement(createTableStatement);

        //建表是否成功返回
        return statement.execute();
    }

//    String theColumnWillDisplayToString(ArrayList<String> ){
//
//    }
@Override
public int insertIntoSomeLine(ArrayList<?> insertArrayOutGet) throws SQLException {

        //构建语句
        switch (getTableId()) {
            case 1 -> {
                if (insertArrayOutGet != null) {
                    ArrayList<Books> insertArray = castToBooksArrayList(insertArrayOutGet) ;
                    for (Books element : insertArray){
                        SqlUtil.executeUpdate(
                                element.getInsertWithoutKeyStatement(),
                                element.Title,
                                element.ISBN,
                                element.Author,
                                element.Publisher,
                                element.ReceiptDate,
                                element.Genre,
                                element.BookQuantity_Visible,
                                element.BookQuantity_Hidden
                        );
                    }
                }
                else {
                    return -1;
                }
            }



            case 2 -> {
                if (insertArrayOutGet != null) {
                    ArrayList<BookCopies> insertArray = castToBookCopiesArrayList(insertArrayOutGet);
                    for (BookCopies element : insertArray) {
                        SqlUtil.executeUpdate(
                                element.getInsertWithoutKeyStatement(),
                                element.BookID,
                                element.CopyNumber,
                                element.AcquisitionDate,
                                element.On_shelfStatus,
                                element.BookLocation,
                                element.IsVisible
                        );
                    }
                }
                else {
                    return -1;
                }
            }
            case 3 -> {
                if (insertArrayOutGet != null) {
                    ArrayList<LibraryCollectionRoom> insertArray = castToLibraryCollectionRoomArrayList(insertArrayOutGet);
                    for (LibraryCollectionRoom element : insertArray){
                        SqlUtil.executeUpdate(
                                element.getInsertWithoutKeyStatement(),
                                element.Room,
                                element.Shelf_ID,
                                element.Compartment_ID,
                                element.InnerNumber
                        );
                    }
                }
                else {
                    return -1;
                }
            }
        }
        return 1;
    }


    //    int deleteSomeLine(ArrayList<?> insertArrayOutGet,OperateType TYPE){
//
//    }
//    ResultSet selectBySomeProperty(ArrayList<?> insertArrayOutGet,OperateType TYPE){
//
//    }
//    boolean updateBySomeProperty(ArrayList<?> insertArrayOutGet,OperateType TYPE){
//
//    }
    BOOK_MANAGER() throws SQLException, ConnectException {    //connect MySQL server
        connection = getConnection();
        createTable();
    }
    public static final String TABLE_CREATE_BOOKS_STATEMENT = """
            CREATE TABLE if NOT EXISTS Books(
             BookID INT AUTO_INCREMENT PRIMARY KEY,
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
                CopyID INT AUTO_INCREMENT PRIMARY KEY,
                BookID INT,
                CopyNumber VARCHAR(50) NOT NULL,
                AcquisitionDate DATE,
                On_shelfStatus BOOLEAN DEFAULT 'TRUE',
                BookLocation VARCHAR(50),
                IsVisible BOOLEAN DEFAULT 'TRUE',
                FOREIGN KEY (BookID) REFERENCES Books(BookID)
            );""";

    public static final String TABLE_CREATE_LIBRARY_COLLECTION_ROOM_STATEMENT = """
                CREATE TABLE IF NOT EXISTS LibraryCollectionRoom (
                CopyID INT PRIMARY KEY,
                Room VARCHAR(10) NOT NULL,
                Shelf_ID CHAR(2) NOT NULL,
                Compartment_ID CHAR(3),
                InnerNumber CHAR(5),
                FOREIGN KEY (CopyID) REFERENCES BookCopies(CopyID)
            );""";

    //获得 Connection
    @Override
    public Connection getConnection(){
        Connection conn;
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/qdu?serverTimezone=GMT%2B8" , "root" , "root");//配置数据库
        try {
            conn =  SqlConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
