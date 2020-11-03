package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to  DB succesfull");
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "12345678");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        SessionFactory sessionFactory;
        try {
            sessionFactory = configuration.addProperties(properties).buildSessionFactory();
            return sessionFactory;
        } catch (Throwable ex) {
            System.out.println("Не удалось подключиться к БД через Hibernate");
            throw new ExceptionInInitializerError(ex);
        }
    }
}
