package test;

import logic.FileHandler;
import model.Book;

import java.util.List;

/**
 * Test the program independent of regular program sequence
 */
public class TestMain {
    //region Constants
    //endregion

    //region Attributes
    //endregion

    //region Constructor
    /**
     * Private constructor to prevent creation of a new object from outside
     */
    private TestMain() {}
    //endregion

    //region Methods
    public static void main(String[] args) {
        System.out.println("TestMain:\n");

        List<Book> books = FileHandler.getInstance().readBooksFromCsvFile();

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void sortBooksByGenre(List<Book> list) {

        list.sort((firstBook, secondBook) -> {
            // Sort Books based on genre
            String firstBookGenre = firstBook.getBookGenre();
            String secondBookGenre = secondBook.getBookGenre();

            int stringCompare = firstBookGenre.compareTo(secondBookGenre);

            return -stringCompare;
        });
    }
    //endregion
}
