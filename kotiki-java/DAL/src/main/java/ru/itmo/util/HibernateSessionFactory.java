package ru.itmo.util;

import ru.itmo.model.Cat;
import ru.itmo.model.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

  private static SessionFactory sessionFactory;

  private HibernateSessionFactory() {

  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Cat.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
      } catch (Exception e) {
        System.out.println("Exception!" + e);
      }
    }
    return sessionFactory;
  }
}
