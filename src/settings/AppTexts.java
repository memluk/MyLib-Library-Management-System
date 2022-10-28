package settings;

public class AppTexts {
    //region Constants
    /**
     * MAIN-MENU messages
     */
    public static final String MAIN_MENU_LIST = "\n[\t" + AppCommands.USER_CMD_LIST + "\t] List the books";
    public static final String MAIN_MENU_SEARCH = "[\t" + AppCommands.USER_CMD_SEARCH + "\t] Search for a book";
    public static final String MAIN_MENU_BORROW = "[\t" + AppCommands.USER_CMD_BORROW + "\t] Borrow a book";
    public static final String MAIN_MENU_RETURN = "[\t" + AppCommands.USER_CMD_RETURN + "\t] Return a book";
    public static final String MAIN_MENU_ADD = "[\t" + AppCommands.USER_CMD_ADD + "\t] Add a new book";
    public static final String MAIN_MENU_EDIT = "[\t" + AppCommands.USER_CMD_EDIT + "\t] Edit a book";
    public static final String MAIN_MENU_DELETE = "[\t" + AppCommands.USER_CMD_DELETE + "\t] Delete a book";
    public static final String MAIN_MENU_EXIT = "[\t" + AppCommands.USER_CMD_EXIT + "\t] End the program";

    /**
     * GENERAL messages
     */
    public static final String MSG_INPUT_BOOK_DATA = "\nPlease, enter the following information... \n";
    public static final String MSG_USER_CHOICE = "\nSelect your option: ";
    public static final String MSG_INVALID_CHOICE = "Invalid Selection!\n";
    public static final String MSG_INVALID_INPUT = "Invalid Input!\n";
    public static final String MSG_INVALID_CHOICE_DIRECTIVE = "\nPlease make a choice from the main menu (0-7): ";
    public static final String MSG_INVALID_YES_OR_NO = "\nPlease enter 'Y' for yes or 'N' for no!: ";

    /**
     * SEARCH-BOOK messages
     */
    public static final String MSG_INPUT_SEARCH = "Enter a term to search: ";
    public static final String MSG_INPUT_SEARCH_ERROR = "\nYour search input is not found in the list!\n";

    /**
     * ADD-BOOK messages
     */
    public static final String MSG_INPUT_TITLE = "Titel of the book: ";
    public static final String MSG_INVALID_TITLE = "\nThe name of the book must have %d to %d characters.\n";
    public static final String MSG_INPUT_AUTHOR = "Name of the author: ";
    public static final String MSG_INVALID_AUTHOR = "\nThe name of the author must have %d to %d characters.\n";
    public static final String MSG_INPUT_GENRE = "\nGenre of the book: ";
    public static final String MSG_INVALID_GENRE = "\nPlease select your choice from the list.\n";

    public static final String MSG_INPUT_YEAR = "Publish year of the book: ";
    public static final String MSG_INVALID_YEAR = "\nPlease enter a 4-digit year, which is earlier than next year but not earlier than 100 years...\n";
    public static final String MSG_BOOK_ADD_SUCCESS = "\nThe book has been successfully added.\n";

    /**
     * DELETE-BOOK messages
     */
    public static final String MSG_CHOOSE_INDEX_DELETE = "\nSelect a book to delete: ";
    public static final String MSG_DELETE_SUCCESS = "\nThe book has been successfully deleted.\n";

    /**
     * EDIT-BOOK messages
     */
    public static final String MSG_CHOOSE_INDEX_EDIT = "\nPlease, select the book, you want to edit: ";
    public static final String MSG_BOOK_EDIT_SUCCESS = "\nThe book has been successfully updated.\n";

    /**
     * BORROW-BOOK messages
     */
    public static final String MSG_CHOOSE_INDEX_BORROW = "\nPlease, select the book, you want to borrow: ";
    public static final String MSG_BOOK_BORROW_SUCCESS = "\nThe books availability has been successfully updated as 'false'.\n";
    public static final String MSG_BOOK_BORROW_UNAVAILABLE = "\nThe book, you want to borrow, is already borrowed!\n";
    public static final String MSG_BOOK_BORROW_ERROR = "\nThe book, you want to borrow, is not in the list!\n";

    /**
     * RETURN-BOOK messages
     */
    public static final String MSG_CHOOSE_INDEX_RETURN = "\nPlease, select the book, you want to return: ";
    public static final String MSG_BOOK_RETURN_SUCCESS = "\nThe books availability has been successfully updated as 'true'.\n";
    public static final String MSG_BOOK_RETURN_ERROR = "\nThe book you want to borrow is not in the list!\n";

    /**
     * SHOW-LIST messages
     */
    public static final String FORMAT_STRING_LIST_HEADER = "\n%5s\t%10s\t%40s\t%25s\t%5s\t%10s\t%10s\n";
    public static final String FORMAT_STRING_LINE = "%5s\t%10s\t%40s\t%25s\t%5s\t%10s\t%15s\n";
    public static final String FORMAT_STRING_LIST_HEADER_LINE = "----------------------------------------------------------------------------------------------------------------------------------";


    public static final String BOOK_ID = "Book-ID";
    public static final String BOOK_GENRE = "Genre";
    public static final String BOOK_TITLE = "Title";
    public static final String AUTHOR_NAME = "Author";
    public static final String PUBLISH_YEAR = "Published";
    public static final String AVAILABILITY = "Available";
    public static final Object DUE_DATE = "Due Date";
    //endregion
}
