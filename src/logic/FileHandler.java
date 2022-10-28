package logic;

import model.Book;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class saves the data as CVS
 */

public class FileHandler {
    //region Constants
    public static final String CSV_FILE_PATH = "src/resources/books.csv";
    public static final String DELIMITER = ";";
    //endregion

    //region Attributes
    private static FileHandler instance;
    //endregion

    //region Constructor
    /**
     * Private constructor to prevent creation of a new object from outside
     */
    private FileHandler() { System.out.println("File-handler generated!\n"); }
    //endregion

    //region Methods
    /**
     * Initialized by first call of the object of the class
     * @return {@link FileHandler} : Only instance of the class
     */
    public static synchronized FileHandler getInstance() {

        // check if the object already exist
        if(instance == null) {
            //if the object not yet exist, create one
            instance = new FileHandler();
        }

        // return the instance
        System.out.println("File-handler returned\n");
        return instance;
    }

    /**
     * Saving books in a CVS-String List
     * @param booksToSave : {@link List < Book >} : List of recorded books
     */
    public void saveBooksToCsvFile(List<Book> booksToSave) {
        //Create a data object
        File csvFile = new File(CSV_FILE_PATH);

        FileOutputStream fos = null;    // define a bridge to the file
        OutputStreamWriter osw = null;  // write in bytes and define the character-set
        BufferedWriter out = null;      // write a String and use the cache for it

        try {
            fos = new FileOutputStream(csvFile);                        // generate fos with data-object
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);  // generate osw with fos and character-set
            out = new BufferedWriter(osw);                              // generate out with osw

            // iterate through the list
            for (Book bookToSave : booksToSave) {
                // write all Books as CVS-Strings in Data
                out.write(bookToSave.getAttributesFromCsvLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                out.flush();    // empty the cache
                out.close();    // close the bridge to the file
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the lines and generate a Book-object for each one and add it to the list.
     * @return {@link List<Book>} : List of Books
     */
    public List<Book> readBooksFromCsvFile() {
        List<Book> bookList = new ArrayList<>();    // create an empty list
        File csvFile = new File(CSV_FILE_PATH);     // create a data-object

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader in = null;

        try {
            if (!csvFile.exists()) {        // check if data exist
                csvFile.createNewFile();    // if it's not exist, create one
            }

            fis = new FileInputStream(csvFile);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            in = new BufferedReader(isr);

            boolean eof = false;    // variable to get to the end of file

            while (!eof) {
                String csvLine = in.readLine();

                if (csvLine == null) {
                    eof = true;
                } else {
                    Book book = new Book(csvLine);
                    bookList.add(book);
                }

            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                in.close();             // close the bridge to the file
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // return the list
        return bookList;
    }
    //endregion
}
