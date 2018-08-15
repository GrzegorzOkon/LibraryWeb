package web;

import dao.entity.Book;
import dao.entity.User;
import manager.Manager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebFault;
import java.util.List;

@WebService
public class LibraryImpl implements Library {
    @WebMethod
    public User logIn(String login, String password) throws WrongUserOrPasswordException
    {
        User user = Manager.getUserFromRepository(login);

        if (user == null) {
            throw new WrongUserOrPasswordException();
        } else {
            if (Manager.verifyPassword(password, user) == false) {
                throw new WrongUserOrPasswordException();
            }
        }

        return user;
    }

    @WebMethod
    public List<Book> search(String title, String author) {
        return Manager.searchForBooks(title, author);
    }

    @WebFault
    public class WrongUserOrPasswordException extends RuntimeException {}
}