package dao;

import interfaces.OwnerDao;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class OwnerDaoImpl implements OwnerDao {

  public Owner findOwnerById(int id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Owner owner = session.get(Owner.class, id);
    session.close();
    return owner;
  }

  public void save(Owner owner) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(owner);
    transaction.commit();
    session.close();
  }

  public void update(Owner owner) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(owner);
    transaction.commit();
    session.close();
  }

  public void delete(Owner owner) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(owner);
    transaction.commit();
    session.close();
  }
}
