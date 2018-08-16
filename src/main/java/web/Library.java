package web;

import dao.entity.Book;
import dao.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface Library {
    @WebMethod
    User logIn(String login, String haslo);

    @WebMethod
    List<Book> search(String title, String author);

    @WebMethod
    List<Book> showRentals(String login);

    @WebMethod
    List<Book> showReservations(String login);
}
