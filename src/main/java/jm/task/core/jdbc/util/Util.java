package jm.task.core.jdbc.util;


import org.hibernate.cfg.Configuration;

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



}
