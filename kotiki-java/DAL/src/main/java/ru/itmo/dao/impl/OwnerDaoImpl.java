package ru.itmo.dao.impl;

import ru.itmo.dao.OwnerDao;
import ru.itmo.model.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.util.HibernateSessionFactory;

public class OwnerDaoImpl implements OwnerDao {

  public Owner findOwnerById(int id) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Owner owner = session.get(Owner.class, id);
    session.close();
    return owner;
  }

  public void save(Owner owner) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(owner);
    transaction.commit();
    session.close();
  }

  public void update(Owner owner) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(owner);
    transaction.commit();
    session.close();
  }

  public void delete(Owner owner) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(owner);
    transaction.commit();
    session.close();
  }
}
