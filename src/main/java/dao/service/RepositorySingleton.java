package dao.service;

import dao.entity.Book;
import dao.entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RepositorySingleton {
    //private static final String SELECT_WYSTAWIONAWERSJA_IN_VERSION = "SELECT w FROM WystawionaWersja w";

    //private static final String INSERT_RAPORT_IN_RAPORTS = "INSERT INTO raports (date, user, raport) VALUES(?, ?, ?)";

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

    /*public void rozpocznijTransakcjê() {
        menedzerEncji.getTransaction().begin();
    }*/

    /*public void zamknijTransakcjê() {
        menedzerEncji.getTransaction().commit();
    }*/

    /*public void insertInReports(String data, String u¿ytkownik, String raport) {
        Query query = menedzerEncji.createNativeQuery("INSERT INTO raports (date, user, raport) VALUES(?, ?, ?)");
        query.setParameter(1, data);
        query.setParameter(2, u¿ytkownik);
        query.setParameter(3, "RAPORT: " + raport);
        query.executeUpdate();
    }*/
}
