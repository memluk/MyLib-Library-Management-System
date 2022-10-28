package model;

import logic.FileHandler;
import static java.lang.Integer.parseInt;

/**
 * Book class is the Model class. It has the following attributes:
 * bookTitle: name of the book
 * authorName: name of the author
 * bookGenre: genre of the book
 * publishYear: publish year of the book
 * bookID:  Every book has a unique ID even if it's deleted.
 * isAvailable: A boolean value to see if the book is available to barrow
 * borrowersID: ID of the library member, who currently keeps the book
 * reservationHoldersID: ID of the library member who wants to reserve the book to borrow next
 */

public class Book {
    //region Constants
    public static String DEFAULT_STR_VAL = ">No Info<";
    public static String DEFAULT_DATE_VAL = " - ";
    public static int DEFAULT_INT_VAL = -1;

    public static final int INDEX_BOOK_ID = 0;
    public static final int INDEX_BOOK_GENRE = 1;
    public static final int INDEX_BOOK_TITLE = 2;
    public static final int INDEX_AUTHOR_NAME = 3;
    public static final int INDEX_PUBLISH_YEAR = 4;
    public static final int INDEX_IS_AVAILABLE = 5;
    public static final int INDEX_DUE_DATE = 6;
    //endregion

    //region Attributes
    private int bookID = DEFAULT_INT_VAL;
    private String bookTitle = DEFAULT_STR_VAL;
    private String authorName = DEFAULT_STR_VAL;
    private String bookGenre = DEFAULT_STR_VAL;
    private int publishYear = DEFAULT_INT_VAL;
    private boolean isAvailable = true;
    private String dueDate = DEFAULT_DATE_VAL;

    //endregion

    //region Constructor
    /**
     * Standard Constructor with defined initial values
     */
    public Book() {
        bookTitle = DEFAULT_STR_VAL;
        authorName = DEFAULT_STR_VAL;
        bookGenre = DEFAULT_STR_VAL;
        publishYear = DEFAULT_INT_VAL;
        bookID = DEFAULT_INT_VAL;
        isAvailable = true;
        dueDate = DEFAULT_DATE_VAL;
    }

    /**
     * Overloaded constructor accepting a CSV line and initializing all attributes using a method.
     * @param csvLine : {@link String} CVS-line
     */
    public Book(String csvLine) {
        setAttributesFromCsvLine(csvLine);
    }

    public Book(int id, String genre,String title, String author, int year, boolean available, String dueDate) {
        this();
        this.bookID = id;
        this.bookGenre = genre;
        this.bookTitle = title;
        this.authorName = author;
        this.publishYear = year;
        this.isAvailable = available;
        this.dueDate = dueDate;
    }
//endregion

    //region Methods
    /**
     * Fill out the attributes with CVS-Strings
     * @return csvLine : {@link String} : Csv-String with Attributes
     */
    private void setAttributesFromCsvLine(String csvLine) {
        String[] allAttributes = csvLine.split(FileHandler.DELIMITER);

        bookID = parseInt(allAttributes[INDEX_BOOK_ID]);
        bookGenre = allAttributes[INDEX_BOOK_GENRE];
        bookTitle = allAttributes[INDEX_BOOK_TITLE];
        authorName = allAttributes[INDEX_AUTHOR_NAME];
        publishYear = parseInt(allAttributes[INDEX_PUBLISH_YEAR]);
        isAvailable = Boolean.parseBoolean(allAttributes[INDEX_IS_AVAILABLE]);
        dueDate = allAttributes[INDEX_DUE_DATE];
    }

    /**
     * Distribute Attributes as CVS
     * @return {@link String} : CVS-line
     */
    public String getAttributesFromCsvLine() {
        return  bookID + FileHandler.DELIMITER +
                bookGenre + FileHandler.DELIMITER +
                bookTitle + FileHandler.DELIMITER +
                authorName + FileHandler.DELIMITER +
                publishYear + FileHandler.DELIMITER +
                isAvailable + FileHandler.DELIMITER +
                dueDate +"\n";
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getDueDate() {
        return dueDate;
    }


    @Override
    public String toString() {
        return "Book{" +
                ", bookID='" + bookID + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                ", publishYear=" + publishYear + '\'' +
                ", isAvailable=" + isAvailable + '\'' +
                ", dueDate =" + dueDate + '\'' +
                '}';
    }

    //endregion
}
