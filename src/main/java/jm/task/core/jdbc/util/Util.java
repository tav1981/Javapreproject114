package jm.task.core.jdbc.util;


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
        /*String hostName = DB_HOSTNAME;

        String dbName = DB_NAME;
        String userName = DB_USERNAME;
        String password = DB_PASSWORD;*/

        return getMySQLConnection(DB_HOSTNAME, DB_NAME, DB_USERNAME, DB_PASSWORD);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password)  {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        //Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
            System.out.println("Соединение с базой удалось");
        } catch (SQLException e) {
            System.out.println("Ошибка соединения");
            throw new RuntimeException(e);
        }
        return conn;
    }
}
