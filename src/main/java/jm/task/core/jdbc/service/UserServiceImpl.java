package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {

    Connection connection = getMySQLConnection();
    public void createUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
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
    }
    //--------------------------------------------------
    public void dropUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS user";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
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
    }
    //--------------------------------------------------
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            /*if (connection != null) {
                connection.close();
            }*/
        }

    }
    //--------------------------------------------------
    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
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
    }
    //--------------------------------------------------
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            /*if (connection != null) {
                connection.close();
            }*/
        }
        return userList;
    }
    //--------------------------------------------------
    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();
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
