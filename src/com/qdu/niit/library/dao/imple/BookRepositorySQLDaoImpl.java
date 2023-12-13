package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.dao.BookRepositorySQLDao;
import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookCopy;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.LibraryCollectionRoom;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
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
    public BookRepositorySQLDaoImpl() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, ConnectException {

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
    public void insert(ArrayList<BookInfo> insertArrayOutGet) throws SQLException, InstantiationException, ObjectHaveNoAttribute {
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

            bookInfo.setBook_id(book_id);
            bookInfo.setCopy_id(copy_id);
            if(bookInfo.getIs_visible()){   //更改图书的数量
                booksManager.INCQuantityOfVisible(book_id);
            }
            else{
                booksManager.INCQuantityOfNotVisible(book_id);
            }
            //别而忘了赋值！！！
            libraryCollectionRoom.setCopy_id(copy_id);
            locationsManager.insert(libraryCollectionRoom);
        }
    }
    @Override
    public void delete(ArrayList<Integer> copy_ids) throws SQLException {

        for(Integer copy_id : copy_ids){
            //最开始先查要删除的书籍
            ArrayList<BookInfo> book = getBookByCopyID(copy_id);
            //先判断是否存在
            locationsManager.delete(copy_id);
            boolean isVisible = bookCopiesManager.getIsVisibleByCopyID(copy_id);
            //book是查询结果
            Integer book_id = book.get(0).getBook_id();
            bookCopiesManager.delete(copy_id);  //删除copies表并获得删除的book_id
            if(isVisible){
                booksManager.DECQuantityOfVisible(book_id);
            }
            else{
                booksManager.DECQuantityOfNotVisible(book_id);
            }
            if(booksManager.isEmpty(book_id)){
                booksManager.delete(book_id);
            }
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
            WHERE c.book_id = ?;
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
    @Override
    public ArrayList<BookInfo> getBookByCopyID(Integer copy_id) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByCopyId(),copy_id);
        return getBookInfos(gotResult, result);
    }
    @Override
    public ArrayList<BookInfo> getBookByDate(Date date) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByDateStatement(),date);
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    @Override
    public ArrayList<BookInfo> getBookByBookID(Integer book_id) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByBookIdStatement(),book_id);
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }

    @Override
    public ArrayList<BookInfo> getBookByAuthor(String author) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByAuthorStatement(),getLikeStatement(author));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    @Override
    public ArrayList<BookInfo> getBookByTitle(String title) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByTitleStatement(),getLikeStatement(title));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }
    @Override
    public ArrayList<BookInfo> getBookByAuthorAndTitle(String author, String title) throws SQLException {
        ArrayList<Object[]> gotResult = null;
        ArrayList<BookInfo> result = new ArrayList<>();
        gotResult = getMany(getGetBookByAuthorAndTitleStatement(),getLikeStatement(author),getLikeStatement(title));
        return getBookInfos(gotResult, result);  //通过Object[]获得ArrayList<BookInfo>
    }

}
