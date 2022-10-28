package uiController;

import logic.FileHandler;
import model.Book;
import settings.AppCommands;
import settings.AppTexts;

import static settings.AppTexts.*;
import static test.TestMain.sortBooksByGenre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UiController {

    //region Attributes
    /**
     * Create a list of Book-Object
     */
    public static List<Book> bookList;
    private final UiInputHandler inputHandler;
    //endregion

    //region Constructor
    public UiController() {
        bookList = FileHandler.getInstance().readBooksFromCsvFile();
        sortBooksByGenre(bookList);
        inputHandler = new UiInputHandler();
    }
    //endregion

    //region Methods
    public void startUi() {
        printAppName();
        handleUserInteraction();
    }

    private void printAppName() {
        System.out.println("""
                ==============================
                =           MyLib            =
                =             by             =
                =        Murat Yaşar         =
                =         (10.2022)          =
                ==============================
                """);
    }
    private void handleUserInteraction() {
        //Declaring a boolean variable as exit condition to limit the do-while loop
        boolean exitApp = false;

        do {
            printMainMenu();

            //Read the users choice from console
            Scanner scanStrObj = new Scanner(System.in);
            String userChoice = scanStrObj.nextLine();

            switch (userChoice) {
                case AppCommands.USER_CMD_LIST -> listBooks();
                case AppCommands.USER_CMD_SEARCH -> searchTerm();
                case AppCommands.USER_CMD_BORROW -> borrowBook();
                case AppCommands.USER_CMD_RETURN -> returnBook();
                case AppCommands.USER_CMD_ADD -> addBook();
                case AppCommands.USER_CMD_EDIT -> editBook();
                case AppCommands.USER_CMD_DELETE -> deleteBook();
                case AppCommands.USER_CMD_EXIT -> exitApp = true;
                default -> {
                    System.err.printf(MSG_INVALID_CHOICE);
                    System.out.printf(AppTexts.MSG_INVALID_CHOICE_DIRECTIVE);
                }
            }

        } while (!exitApp);
    }

    /**
     * Shows the main menu and the options to pick to the user
     */
    private void printMainMenu() {
        System.out.println(AppTexts.MAIN_MENU_LIST);
        System.out.println(AppTexts.MAIN_MENU_SEARCH);
        System.out.println(AppTexts.MAIN_MENU_BORROW);
        System.out.println(AppTexts.MAIN_MENU_RETURN);
        System.out.println(AppTexts.MAIN_MENU_ADD);
        System.out.println(AppTexts.MAIN_MENU_EDIT);
        System.out.println(AppTexts.MAIN_MENU_DELETE);
        System.out.println(AppTexts.MAIN_MENU_EXIT);
        System.out.printf(AppTexts.MSG_USER_CHOICE);
    }

    /**
     * Print all the books to the screen
     */
    private void listBooks() {
        System.out.printf(AppTexts.FORMAT_STRING_LIST_HEADER, AppTexts.BOOK_ID,
                AppTexts.BOOK_GENRE, AppTexts.BOOK_TITLE, AppTexts.AUTHOR_NAME,
                AppTexts.PUBLISH_YEAR, AppTexts.AVAILABILITY, AppTexts.DUE_DATE);
        System.out.println(FORMAT_STRING_LIST_HEADER_LINE);

        for (int i = 0; i < bookList.size(); i++) {
            Book currentBook = bookList.get(i);
                System.out.printf(AppTexts.FORMAT_STRING_LINE, currentBook.getBookID(),
                        currentBook.getBookGenre(), currentBook.getBookTitle(),
                        currentBook.getAuthorName(), currentBook.getPublishYear(),
                        currentBook.isAvailable(), currentBook.getDueDate());
        }
    }

    /**
     * Receive an input from console to search in the list
     */
    private void searchTerm() {
        System.out.printf(AppTexts.MSG_INPUT_SEARCH);

        // Create a searchResultsCount to print number of results
        int searchResultCount = 0;

        Scanner scanStr = new Scanner(System.in);
        String searchInput = scanStr.nextLine();

        // Print a header for the search results
        System.out.printf(AppTexts.FORMAT_STRING_LIST_HEADER, AppTexts.BOOK_ID,
                AppTexts.BOOK_GENRE, AppTexts.BOOK_TITLE, AppTexts.AUTHOR_NAME,
                AppTexts.PUBLISH_YEAR, AppTexts.AVAILABILITY);
        System.out.println("----------------------------------------------------------------------------------------------------");

        for(Book item: bookList) {
            if(item.getBookTitle().contains(searchInput)) {
                System.out.printf(AppTexts.FORMAT_STRING_LINE, item.getBookID(),
                        item.getBookGenre(), item.getBookTitle(), item.getAuthorName(),
                        item.getPublishYear(), item.isAvailable());
                searchResultCount++;
            } else if (item.getAuthorName().contains(searchInput)) {
                System.out.printf(AppTexts.FORMAT_STRING_LINE, item.getBookID(),
                        item.getBookGenre(), item.getBookTitle(), item.getAuthorName(),
                        item.getPublishYear(), item.isAvailable());
                searchResultCount++;
            }
        }

        if(searchResultCount == 0) System.err.printf(MSG_INPUT_SEARCH_ERROR);
        else System.out.println("There are " + searchResultCount + " results listed...");
    }

    /**
     * receive an index from the console and sets the books availability as 'false'
     */
    private void borrowBook() {
        // Show the current list
        listBooks();

        // Read index  of the book to borrow
        System.out.printf(AppTexts.MSG_CHOOSE_INDEX_BORROW);
        Scanner scanStr = new Scanner(System.in);
        String indexStr = scanStr.nextLine();
        int indexInt = Integer.parseInt(indexStr);

        // Set a bookCount to check if the book is in the list
        int bookCount = 0;

        for (int i=0; i<bookList.size(); i++) {

            if(bookList.get(i).getBookID() == indexInt && bookList.get(i).isAvailable() == true) {
                // Set availability
                bookList.get(i).setAvailable(false);

                // Create a due date to return the book
                String dueDate = createDueDate();
                //Set a due date
                bookList.get(i).setDueDate(dueDate);

                System.out.println("Book-ID: " + indexInt);
                System.out.println(MSG_BOOK_BORROW_SUCCESS);
            } else if(bookList.get(i).getBookID() == indexInt && bookList.get(i).isAvailable() == false) {
                System.out.println("Book-ID: " + indexInt);

                //Error messages
                System.err.println(MSG_BOOK_BORROW_UNAVAILABLE);
                System.out.println("The due date of the book is: " + bookList.get(i).getDueDate() + "!");
            } else {bookCount++;}
        }

        // Error Message if the book is not in the list
        if(bookCount == bookList.size()) {
            System.err.printf(MSG_BOOK_BORROW_ERROR);
        }

        sortAndSaveListInCsvFile();
    }

    private String createDueDate() {
        // Create a calender object
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = dateObj.format(formatObj);

        // Read today's date
        LocalDate today = LocalDate.parse(formattedDate);

        // Add 30 days to obtain due date
        LocalDate dueDate = today.plusDays(30);

        // convert date element to a String and return
        return dueDate.toString();
    }

    /**
     * receive an index from the console and sets the books availability as 'true'
     */
    private void returnBook() {
        // Show the current list
        listBooks();

        // Read index  of the book to return
        System.out.println(AppTexts.MSG_CHOOSE_INDEX_RETURN);
        Scanner scanStr = new Scanner(System.in);
        String indexStr = scanStr.nextLine();
        int indexInt = Integer.parseInt(indexStr);

        // Set a bookCount to check if the book is in the list
        int bookCount = 0;

        for (int i=0; i<bookList.size(); i++) {
            if(bookList.get(i).getBookID() == indexInt) {
                bookList.get(i).setAvailable(true);
                System.out.println("Book-ID: " + indexInt);
                System.out.println(MSG_BOOK_RETURN_SUCCESS);
            } else {bookCount++;}
        }

        if(bookCount == bookList.size()) {
            System.out.println(MSG_BOOK_RETURN_ERROR);
        }

        sortAndSaveListInCsvFile();
    }

    /**
     * Add a new book to the list
     */
    private void addBook() {
        Book book = inputHandler.getBookFromConsole();  //Read notes
        bookList.add(book); //Add notes to the list
        sortAndSaveListInCsvFile();
        System.out.println(AppTexts.MSG_BOOK_ADD_SUCCESS);
    }

    /**
     * Let user choose a book to edit than read the new input and change the content
     */
    private void editBook() {
        // Show book-list
        listBooks();

        // Read bookID to edit
        Scanner scanStr = new Scanner(System.in);
        String indexStr = scanStr.nextLine();
        int indexInt = Integer.parseInt(indexStr);
        int indexCount = 0;

        for (int i=0; i<bookList.size(); i++) {
            if(bookList.get(i).getBookID() == indexInt) {

                // Read new book
                Book book = inputHandler.getBookFromConsole();

                // Replace the book
                bookList.set(i, book);
                System.out.println("Book-ID: " + indexInt);
                System.out.println(AppTexts.MSG_BOOK_EDIT_SUCCESS);

            } else {indexCount++;}
        }

        // Error Message if the bookID not found
        if (indexCount == bookList.size()) {
            System.err.printf(MSG_INVALID_CHOICE);
        }

        sortAndSaveListInCsvFile();
    }

    /**
     * Get an index from console to delete an existing book
     */
    private void deleteBook() {
        System.out.println(AppTexts.MSG_CHOOSE_INDEX_DELETE);
        listBooks();    // Read the current list

        // Read id  of the book to delete
        Scanner scanStr = new Scanner(System.in);
        String indexStr = scanStr.nextLine();
        int indexInt = Integer.parseInt(indexStr);

        for (int i=0; i<bookList.size(); i++) {
            if(bookList.get(i).getBookID() == indexInt) {
                bookList.remove(i);
                System.out.println("Book-ID: " + indexInt);
                System.out.println(MSG_DELETE_SUCCESS);
            }
        }

        sortAndSaveListInCsvFile();
    }

    /**
     * Sorts and saves the book-list as a CVS file
     */
    private void sortAndSaveListInCsvFile() {
        sortBooksByGenre(bookList);
        FileHandler.getInstance().saveBooksToCsvFile(bookList);
    }

    //endregion
}
