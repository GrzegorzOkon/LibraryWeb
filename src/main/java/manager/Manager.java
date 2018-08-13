package manager;

import dao.entity.User;
import dao.service.RepositorySingleton;

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

    public static boolean verifyPassword(String password, User user) {
        return password.equals(user.getPassword()) ? true : false;
    }
}
