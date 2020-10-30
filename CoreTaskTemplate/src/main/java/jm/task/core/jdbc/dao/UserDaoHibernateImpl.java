package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl extends Util implements UserDao {

    SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE Users (id MEDIUMINT NOT NULL AUTO_INCREMENT, name VARCHAR(225), lastName VARCHAR(225), age INT, PRIMARY KEY (id))";
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("created table user");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE Users;";
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("drop table user");
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Таблицы с таким именем не существует");
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем " + name + " успешно добавлен в БД");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
            System.out.println("remove user by id " + id);
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            list = session.createQuery("SELECT a FROM User a", User.class).getResultList();
            transaction.commit();
            System.out.println("select from");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("DELETE from User").executeUpdate();
            transaction.commit();
            System.out.println("DELETE from user");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
