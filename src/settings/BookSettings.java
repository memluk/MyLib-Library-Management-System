package settings;

/**
 * This class contains the general settings of user-input
 */
public class BookSettings {
    //region Constants
    /**
     * List of the book genres has been limited to 9 just to keep it simple.
     * We use a 2-dimensional array, which first element keeps the index of the genre,
     * which is given as the second element.
      */
    public static final String[][] genreList = {
            {"1", "Adventure"},
            {"2", "Biography"},
            {"3", "Classics"},
            {"4", "Detective"},
            {"5", "Fantasy"},
            {"6", "Horror"},
            {"7", "Romance"},
            {"8", "Sci-Fiction"},
            {"9", "Non-Fiction"}
    };

    // Define an array to keep the index of the last book in the given genre
    public static int bookId = 0;

    public static final int MAX_LENGTH_TITLE = 50;
    public static final int MAX_LENGTH_AUTHOR = 25;
    public static final int MIN_LENGTH_STR = 1;
    //endregion

    //region Attributes

    //endregion

    //region Constructor
    /**
     * Private constructor to prevent creation of a new object from outside
     */
    private BookSettings() {}
    //endregion

    //region Methods
    //endregion
}
