package dao.service;

import dao.entity.Book;
import dao.entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class RepositorySingleton {
    private static RepositorySingleton repository;

    private static SessionFactory factory;

    private RepositorySingleton() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static RepositorySingleton getInstance() {
        if (repository == null) {
            repository = new RepositorySingleton();
        }

        return repository;
    }

    public User findUser(String login) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        Object result = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM User U WHERE U.login = :user_login";
            Query query = session.createQuery(hql);
            query.setParameter("user_login", login);
            result = query.getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return (User)result;
    }

    public List<Book> searchByTitle(String title) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Book> result = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM Book B WHERE B.title = :book_title";
            Query query = session.createQuery(hql);
            query.setParameter("book_title", title);
            result = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Book> searchByAuthor(String author) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Book> result = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM Book B WHERE B.author = :book_author";
            Query query = session.createQuery(hql);
            query.setParameter("book_author", author);
            result = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Book> searchByTitleAndAuthor(String title, String author) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Book> result = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM Book B WHERE B.title = :book_title AND B.author = :book_author";
            Query query = session.createQuery(hql);
            query.setParameter("book_title", title);
            query.setParameter("book_author", author);
            result = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Book> showRentals(String login) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Book> result = null;

        try {
            tx = session.beginTransaction();

            String sql = "select b.* from books b, user u, rentals r where b.id = r.book_id and r.user_login = u.login and u.login = :user_login";
            Query query = session.createSQLQuery(sql).addEntity(Book.class).setParameter("user_login", login);
            result = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Book> showReservations(String login) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Book> result = null;

        try {
            tx = session.beginTransaction();

            String sql = "select b.* from books b, user u, reservations r where b.id = r.book_id and r.user_login = u.login and u.login = :user_login";
            Query query = session.createSQLQuery(sql).addEntity(Book.class).setParameter("user_login", login);
            result = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public void addReservation(String login, int bookId) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "insert into reservations values(:book_id, :user_login)";
            Query query = session.createSQLQuery(sql).setParameter("book_id", bookId).setParameter("user_login", login);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void cancelReservation(String login, int bookId) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String sql = "delete from reservations where book_id = :book_id AND user_login = :user_login";
            Query query = session.createSQLQuery(sql).setParameter("book_id", bookId).setParameter("user_login", login);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}