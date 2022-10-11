package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl  implements UserDao {

    //Util util2 = new Util();
    //Connection connection = util2.getMySQLConnection();
    Session session = Util.getHibernateSession();

    public UserDaoHibernateImpl() {

    }

    //Session session = getHibernateSession();
    @Override
    public void createUsersTable() {
        //session.getSession();
        //session.close();
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }

    @Override
    public String getUserNameById(Long id)  {
        return  "совсем не вася";
    }

}
