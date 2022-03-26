package dao;

import interfaces.CatDao;
import models.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class CatDaoImpl implements CatDao {

  public Cat findCatById(int id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Cat cat = session.get(Cat.class, id);
    session.close();
    return cat;
  }

  public void save(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(cat);
    transaction.commit();
    session.close();
  }

  public void update(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(cat);
    transaction.commit();
    session.close();
  }

  public void delete(Cat cat) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(cat);
    transaction.commit();
    session.close();
  }
}
