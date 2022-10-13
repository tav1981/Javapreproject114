package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl  implements UserDao {

    SessionFactory sessionFactory = Util.getHibernateSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.save(user);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            session.delete(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List users = session.createQuery("FROM User").list();
            transaction.commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM user";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public String getUserNameById(Long id)  {
        try (Session session = sessionFactory.openSession()) {
            User user =  session.get(User.class, id);
            return user.getName();
        }
    }

}
