package test;

import model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a static Method to generate data
 */
public class TestData {
    //region Constants
    private static final int TEST_BOOK_AMOUNT = 2;
    //endregion

    //region Attributes
    //endregion

    //region Constructor
    /**
     * Private constructor to prevent creation of a new object from outside
     */
    private TestData() {}
    //endregion

    //region Methods
    /**
     * Generate test-books and print them
     * @return {@Link Note} : testNoteList ArrayList
     */
    public static List<Book> getTestBooks() {
        List<Book> testBookList = new ArrayList<>();

        for (int i = 0; i < TEST_BOOK_AMOUNT; i++) {
            Book testBook = new Book(i, "NoGenre", "Test-Title", "Test-author", 2022, true, " - ");
            if (i % 2 == 0) testBook.setAvailable(false);
            testBookList.add(testBook);
        }
        return testBookList;
    }
    //endregion
}
