package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_NAME = "myDb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1";

    //инициализаця хибернет не через xml-------------------------
    //private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;


    static {
        Configuration configuration = new Configuration();

        // Creating StandardServiceRegistryBuilder
        //StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings which is equivalent to hibernate.cfg.xml's properties
        //Map<String, String> dbSettings = new HashMap<>();
        Properties dbSettings = new Properties();
        dbSettings.put(Environment.URL, "jdbc:mysql://" + DB_HOSTNAME + ":3306/" + DB_NAME);
        dbSettings.put(Environment.USER, DB_USERNAME);
        dbSettings.put(Environment.PASS, DB_PASSWORD);
        dbSettings.put(Environment.DRIVER, DB_DRIVER);
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        dbSettings.put(Environment.SHOW_SQL, "true");

        dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        dbSettings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(dbSettings);

        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // Apply database settings
        //registryBuilder.applySettings(dbSettings);
        // Creating registry
        //standardServiceRegistry = registryBuilder.build();
        // Creating MetadataSources
        //MetadataSources sources = new MetadataSources(standardServiceRegistry);
        // Creating Metadata
        //Metadata metadata = sources.getMetadataBuilder().build();
        // Creating SessionFactory
        //sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    //------------------------------------------------------------

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

    public static SessionFactory getHibernateSession() {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
