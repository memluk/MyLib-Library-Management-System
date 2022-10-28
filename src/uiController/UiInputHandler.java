package uiController;

import model.Book;
import settings.AppTexts;
import settings.BookSettings;

import java.time.YearMonth;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static model.Book.*;
import static settings.AppTexts.*;
import static settings.BookSettings.genreList;
import static uiController.UiController.bookList;

/**
 * This is side-class of {@link UiController}.
 * It reads the user-inputs checks the validity.
 * Returns the valid inputs to {@link UiController}.
 */
public class UiInputHandler {
    //region Attributes
    //endregion

    //region Methods
    /**
     * Read the user-input from console and return
     * @return {@link Book} : Read Book
     */
    public Book getBookFromConsole() {
        System.out.println(AppTexts.MSG_INPUT_BOOK_DATA);

        String title = getValidBookTitle();
        String author = getValidAuthorName();
        String genre = getValidGenre();
        String date = " - ";

        int id = 0;
        if (bookList.size() != 0) {
            id = createBookID();
        } else {
            id = 1;
        }

        int year = getValidYear();
        boolean availability = true;

        return new Book(id, genre, title, author, year, availability, date);
    }

    /**
     * Read the title of the book from console and check the validity (Min. 1, Max. 50 characters).
     * @return {@link String} : Title of the book
     */
    private String getValidBookTitle() {
        String title = DEFAULT_STR_VAL;
        boolean isValid = false;

        while (!isValid) {
            System.out.printf(AppTexts.MSG_INPUT_TITLE);

            Scanner scanStr = new Scanner(System.in);
            title = scanStr.nextLine();

            if (title.length() >= BookSettings.MIN_LENGTH_STR && title.length() <= BookSettings.MAX_LENGTH_TITLE) {
                isValid = true;
            } else {
                System.err.printf(MSG_INVALID_TITLE, BookSettings.MIN_LENGTH_STR, BookSettings.MAX_LENGTH_TITLE);
            }
        }
        return title;
    }

    /**
     * Read the name of the book from the console and check its validity.
     * @return {@link String} : Name of the author
     */
    private String getValidAuthorName() {
        String author = DEFAULT_STR_VAL;
        boolean isValid = false;

        while (!isValid) {
            System.out.printf(AppTexts.MSG_INPUT_AUTHOR);

            Scanner scanStr = new Scanner(System.in);
            author = scanStr.nextLine();

            if (author.length() >= BookSettings.MIN_LENGTH_STR && author.length() <= BookSettings.MAX_LENGTH_AUTHOR) {
                isValid = true;
            } else {
                System.err.printf(MSG_INVALID_TITLE, BookSettings.MIN_LENGTH_STR, BookSettings.MAX_LENGTH_AUTHOR);
            }
        }
        return author;
    }
    private String getValidGenre() {
        String genre = DEFAULT_STR_VAL;
        boolean isValid = false;

         do {
            for(int i=0; i<genreList.length; i++) {
                for (int j=0; j<genreList[i].length; j++) {
                    System.out.printf("%s\t", genreList[i][j]);
                }
                System.out.printf("\n");
            }

             System.out.printf(AppTexts.MSG_INPUT_GENRE);

            Scanner scanStr = new Scanner(System.in);
            String indexStr = scanStr.nextLine();

            switch (indexStr) {
                case "1" : genre = "Adventure"; isValid = true; break;
                case "2" : genre = "Biography"; isValid = true; break;
                case "3" : genre = "Classics"; isValid = true; break;
                case "4" : genre = "Detective"; isValid = true; break;
                case "5" : genre = "Fantasy"; isValid = true; break;
                case "6" : genre = "Horror"; isValid = true; break;
                case "7" : genre = "Romance"; isValid = true; break;
                case "8" : genre = "Sci-Fiction"; isValid = true; break;
                case "9" : genre = "Non-Fiction"; isValid = true; break;
                default: System.out.printf(AppTexts.MSG_INVALID_GENRE);
            }
        } while (!isValid);

        return genre;
    }
    private int getValidYear() {
        boolean isValid = false;
        String inputYear;

        do {
            System.out.printf(AppTexts.MSG_INPUT_YEAR);

            int thisYear = YearMonth.now().getYear();

            Scanner scanStr = new Scanner(System.in);
            inputYear = scanStr.nextLine();

            int year = parseInt(inputYear);

            // The books, older than 100 years, are protected in a National Libraries and cannot be borrowed!
            if (year <= thisYear && year > thisYear-100) {
                isValid = true;
            } else {
                System.err.printf(MSG_INVALID_YEAR);
            }
        } while (!isValid);
        return parseInt(inputYear);
    }

    /**
     * It finds the biggest bookID in the array and return it after increasing by one.
     * The biggest ID is the last one given as ID. The regular increment will fail,
     * because of automatic sort after adding a new book to the list.
     * @return {@link int} : the biggest bookID in the bookList.
     */
    public static int createBookID() {
        int lastBookId = bookList.get(0).getBookID();

        for (Book item: bookList) {
            if (item.getBookID() > lastBookId) lastBookId = item.getBookID();
        }

        return ++lastBookId;
    }
    //endregion
}
