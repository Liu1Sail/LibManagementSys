import bookmanager.Books;
import bookmanager.BooksManager;

import java.awt.print.Book;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookTest {
    public static void main(String[] args) throws SQLException, ConnectException {
        BooksManager booksManager = new BooksManager();
        long timestamp = System.currentTimeMillis(); // 获取当前时间的时间戳
        Date date = new Date(timestamp);
        Books book = new Books("C++","1111-23123","Apollo","青岛大学出版社",date,"计算机",10,1);
        ArrayList<Books> BooksList = new ArrayList<>();
        BooksList.add(book);
        booksManager.insertIntoSomeLine(BooksList);
    }
}
