package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static Session getHibernateSession() {
        Configuration myConfig = new Configuration();
        myConfig.configure();

        try(SessionFactory sessionFactory = myConfig.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            System.out.println("Cессия запущена");
            return session;
        }

    }
}
