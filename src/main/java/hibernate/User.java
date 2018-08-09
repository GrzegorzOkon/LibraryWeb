package hibernate;

public class User {
    public final String login, password, firstName, surname;

    public User(String login, String password, String firstName, String surname) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
    }
}
