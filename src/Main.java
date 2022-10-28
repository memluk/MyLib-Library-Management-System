import uiController.UiController;

/**
 * MyLib is an electronic library system, which keeps the records of
 * the current books and the members of the library.
 * You can see the list of the books, add a new book, edit or delete
 * an existing book or search for a book based on the name of the book
 * or the authors name.
 */

public class Main {
    public static void main(String[] args) {
        UiController uiController = new UiController();
        uiController.startUi();
    }
}