package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    Util util = new Util();
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    Connection connection = util.getMySQLConnection();
    public void createUsersTable() throws SQLException {
        userDaoJDBC.createUsersTable();
    }
    //--------------------------------------------------
    public void dropUsersTable() throws SQLException {
       userDaoJDBC.dropUsersTable();
    }
    //--------------------------------------------------
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBC.saveUser(name, lastName, age);
    }
    //--------------------------------------------------
    public void removeUserById(long id) throws SQLException {
        userDaoJDBC.removeUserById(id);
    }
    //--------------------------------------------------
    public List<User> getAllUsers() throws SQLException {
        return userDaoJDBC.getAllUsers();
    }
    //--------------------------------------------------
    public void cleanUsersTable() throws SQLException {
        userDaoJDBC.cleanUsersTable();
    }
    //--------------------------------------------------
    public String getUserNameById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        String name = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            /*if (connection != null) {
                connection.close();
            }*/
        }
        return name;
    }
}
