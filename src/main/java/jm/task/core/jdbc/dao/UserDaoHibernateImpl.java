package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl  implements UserDao {

    Session session = Util.getHibernateSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        transaction.commit();
        //session.close();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        transaction.commit();
        //session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session.save(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List users = session.createQuery("FROM USER").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM user";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        transaction.commit();
    }

    @Override
    public String getUserNameById(Long id)  {
        User user = (User) session.get(User.class, id);
        return user.getName();
    }

}
