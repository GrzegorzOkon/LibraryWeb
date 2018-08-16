package manager;

import dao.entity.Book;
import dao.entity.User;
import dao.service.RepositorySingleton;

import java.util.List;

public class Manager {
    public static User getUserFromRepository(String login) {
        User user = null;

        try {
            user = RepositorySingleton.getInstance().findUser(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static List<Book> searchForBooks(String title, String author) {
        List<Book> books = null;

        try {
            if (!title.equals("") && author.equals("")) {
                books = RepositorySingleton.getInstance().searchByTitle(title);
            } else if (title.equals("") && !author.equals("")) {
                books = RepositorySingleton.getInstance().searchByAuthor(author);
            } else {
                books = RepositorySingleton.getInstance().searchByTitleAndAuthor(title, author);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return books;
    }

    public static List<Book> showRentals(String login) {
        List<Book> books = null;

        try {
            books = RepositorySingleton.getInstance().showRentals(login);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public static List<Book> showReservations(String login) {
        List<Book> books = null;

        try {
            books = RepositorySingleton.getInstance().showReservations(login);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public static void addReservation(String login, int bookId) {
        try {
            RepositorySingleton.getInstance().addReservation(login, bookId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyPassword(String password, User user) {
        return password.equals(user.getPassword()) ? true : false;
    }

    public static void cancelReservation(String login, int bookId) {
        try {
            RepositorySingleton.getInstance().cancelReservation(login, bookId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
