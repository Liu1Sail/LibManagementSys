package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.Exception.objectHaveNoAttribute;
import com.qdu.niit.library.dao.BaseSQLDao;
import com.qdu.niit.library.dao.BookRepositorySQLDao;
import com.qdu.niit.library.dao.BooksSQLDao;
import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookCopy;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookRepositorySQLDaoImpl extends BaseSQLDaoImpl implements  BookRepositorySQLDao {

    BooksSQLDaoImpl booksManager;
    BookCopiesSQLDaoImpl bookCopiesManager;
    LibraryCollectionRoomSQLDaoImpl locationsManager;

    /**
     * 构造函数
     */
    BookRepositorySQLDaoImpl() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, ConnectException {

//        Class<?> BooksManager = Class.forName("com.qdu.niit.library.dao.imple.BooksSQLDaoImpl");
//        Constructor<?> constructorOfBooksManager = BooksManager.getDeclaredConstructor(int.class);
//        constructorOfBooksManager.setAccessible(true);
//        BooksSQLDaoImpl booksManager = (BooksSQLDaoImpl) constructorOfBooksManager.newInstance(1);
//
//        Class<?> BookCopiesManager = Class.forName("com.qdu.niit.library.dao.imple.BookCopiesSQLDaoImpl");
//        Constructor<?> constructorOfBookCopiesManager = BookCopiesManager.getDeclaredConstructor(int.class);
//        constructorOfBookCopiesManager.setAccessible(true);
//        BookCopiesSQLDaoImpl bookCopiesSQLDao = (BookCopiesSQLDaoImpl) constructorOfBookCopiesManager.newInstance(1);
//
//        Class<?> LocationsManager = Class.forName("com.qdu.niit.library.dao.imple.LibraryCollectionRoomSQLDaoImpl");
//        Constructor<?> constructorOfLocationsManager = LocationsManager.getDeclaredConstructor(int.class);
//        constructorOfLocationsManager.setAccessible(true);
//        LibraryCollectionRoomSQLDaoImpl locationsManager = (LibraryCollectionRoomSQLDaoImpl) constructorOfLocationsManager.newInstance(1);
        booksManager = new BooksSQLDaoImpl();
        bookCopiesManager = new BookCopiesSQLDaoImpl();
        locationsManager = new LibraryCollectionRoomSQLDaoImpl();
    }
    @Override
    public void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException, InstantiationException, objectHaveNoAttribute {
        Integer book_id;
        Integer copy_id;

        for(BookInfo bookInfo : insertArrayOutGet){
            //先判断是否存在
            book_id = booksManager.getIfBookIsExistByISBN(bookInfo.getIsbn());
            Book book = new Book(bookInfo);
            BookCopy bookCopy = new BookCopy(bookInfo);
            LibraryCollectionRoom libraryCollectionRoom = new LibraryCollectionRoom(bookInfo);
            //不存在先创建book
            if(book_id == -1){
                book_id = booksManager.insert(book);
            }
            book.setBook_id(book_id);  //设置book和copy的book_id
            bookCopy.setBook_id(book_id);

            copy_id = bookCopiesManager.insertToBookCopies(bookCopy);
            bookCopy.setCopy_id(copy_id);

            if(bookInfo.getIs_visible()){   //更改图书的数量
                booksManager.INCQuantityOfVisible(book_id);
            }
            else{
                booksManager.INCQuantityOfNotVisible(book_id);
            }

            locationsManager.insert(libraryCollectionRoom);
        }
    }
    private static final String GET_BOOK_BY_COPY_ID_STATEMENT = """
            SELECT
                b.book_id,
                c.copy_id,
                b.title,
                b.isbn,
                b.author,
                b.publisher,
                b.receipt_date,
                b.genre,
                c.acquisition_date,
            	l.room,
                c.book_location,
            	c.on_shelf_status,
                c.is_visible
            FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
            WHERE c.copy_id = ?;
            """;

    public static String getGetBookByCopyId() {
        return GET_BOOK_BY_COPY_ID_STATEMENT;
    }
    private static final String GET_BOOK_BY_Date_STATEMENT = """
            SELECT b.book_id,c.copy_id, b.title,
                b.isbn,
                b.author,
                b.publisher,
                b.receipt_date,
                b.genre,
                c.acquisition_date,
            	l.room,
                c.book_location,
            	c.on_shelf_status,
                c.is_visible
            FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
            WHERE c.acquisition_date = ?;
            """;

    public static String getGetBookByDateStatement() {
        return GET_BOOK_BY_Date_STATEMENT;
    }
    private static final String GET_BOOK_BY_BOOK_ID_STATEMENT = """
            SELECT
                b.book_id,
                c.copy_id,
                b.title,
                b.isbn,
                b.author,
                b.publisher,
                b.receipt_date,
                b.genre,
                c.acquisition_date,
            	l.room,
                c.book_location,
            	c.on_shelf_status,
                c.is_visible
            FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
            WHERE c.book = ?;
            """;

    public static String getGetBookByBookIdStatement() {
        return GET_BOOK_BY_BOOK_ID_STATEMENT;
    }

    private static final String GET_BOOK_BY_AUTHOR_STATEMENT = """
            SELECT b.book_id,c.copy_id, b.title,
                                 b.isbn,
                                 b.author,
                                 b.publisher,
                                 b.receipt_date,
                                 b.genre,
                                 c.acquisition_date,
                             		l.room,
                                 c.book_location,
                             		c.on_shelf_status,
                                 c.is_visible
                             FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
                             WHERE b.author LIKE ?;
            """;

    public static String getGetBookByAuthorStatement() {
        return GET_BOOK_BY_AUTHOR_STATEMENT;
    }
    private static final String GET_BOOK_BY_TITLE_STATEMENT = """
            SELECT b.book_id,c.copy_id, b.title,
                b.isbn,
                b.author,
                b.publisher,
                b.receipt_date,
                b.genre,
                c.acquisition_date,
            	l.room,
                c.book_location,
            	c.on_shelf_status,
                c.is_visible
            FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
            WHERE b.title LIKE ?;
            """;

    public static String getGetBookByTitleStatement() {
        return GET_BOOK_BY_TITLE_STATEMENT;
    }
    private static final String GET_BOOK_BY_AUTHOR_AND_TITLE_STATEMENT = """
            SELECT b.book_id,c.copy_id, b.title,
                                         b.isbn,
                                         b.author,
                                         b.publisher,
                                         b.receipt_date,
                                         b.genre,
                                         c.acquisition_date,
                                     		l.room,
                                         c.book_location,
                                     		c.on_shelf_status,
                                         c.is_visible
                                     FROM Books b INNER JOIN BookCopies c ON b.book_id = c.book_id INNER JOIN LibraryCollectionRoom l ON c.copy_id = l.copy_id
                                     WHERE b.author LIKE ? && b.title LIKE ?;
            """;

    public static String getGetBookByAuthorAndTitleStatement() {
        return GET_BOOK_BY_AUTHOR_AND_TITLE_STATEMENT;
    }
    private String getLikeStatement(String keyWord){
        return "%" + keyWord + "%";
    }
    public ArrayList<BookInfo> getBookByCopyID(Integer copy_id) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByCopyId(),copy_id);
        return getBookInfos(gotResult, result);
    }
    public ArrayList<BookInfo> getBookByDate(Date date) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByDateStatement(),date);
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    public ArrayList<BookInfo> getGetBookByBookID(Integer book_id) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByBookIdStatement(),book_id);
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }

    public ArrayList<BookInfo> getBookByAuthor(String author) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByAuthorStatement(),getLikeStatement(author));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    public ArrayList<BookInfo> getBookByTitle(String title) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByTitleStatement(),getLikeStatement(title));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    public ArrayList<BookInfo> getBookByAuthorAndTitle(String author,String title) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByAuthorAndTitleStatement(),getLikeStatement(author),getLikeStatement(title));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }

    private ArrayList<BookInfo> getBookInfos(ArrayList<Object[]> gotResult, ArrayList<BookInfo> result) {
        BookInfo bookInfo;
        for (Object[] obj : gotResult){
            bookInfo = new BookInfo((Integer)obj[0],(Integer)obj[1],(String) obj[2],(String)obj[3],(String)obj[4],(String)obj[5],(Date)obj[6],(String)obj[7],(Date)obj[8],(String)obj[9],(String)obj[10],(Boolean)obj[11],(Boolean)obj[12]);
            result.add(bookInfo);
        }
        return result;
    }

}
