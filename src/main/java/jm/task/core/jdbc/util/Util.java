package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_NAME = "myDb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1";

    //для того чтобы нельзя было создать объект утильного класса
    private Util() {
    }

    // Connect to MySQL
    public static Connection getMySQLConnection()  //throws SQLException, ClassNotFoundException
     {
         try {
             Class.forName(DB_DRIVER);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }

         String connectionURL = "jdbc:mysql://" + DB_HOSTNAME + ":3306/" + DB_NAME;

         Connection conn = null;
         try {
             conn = DriverManager.getConnection(connectionURL, DB_USERNAME,
                     DB_PASSWORD);
             System.out.println("Соединение с базой удалось");
         } catch (SQLException e) {
             System.out.println("Ошибка соединения");
             throw new RuntimeException(e);
         }
         return conn;
    }

    public static Session getHibernateSession() {

        //Configuration myConfig = new Configuration();
        //myConfig.configure(new File(getClass().getClassLoader().getResource("hibernate.cfg.xml").toURI()));
        //myConfig.configure("/resources/hibernate.cfg.xml");
        //myConfig.configure(new File("/resources/hibernate.cfg.xml"));
        //sessionFactory = new Configuration().configure().buildSessionFactory();
        //myConfig.configure();
        System.out.println("aaaaaaaaaaaaaaaaaaa------------------------------------");

        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            System.out.println("Cессия запущена");
            //User user =
            return session;
        }

        //return null;

    }


}
