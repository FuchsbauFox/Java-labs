package dao;

import inter.CatDao;
import model.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

public class CatDaoImpl implements CatDao {

  public Cat findCatById(int id) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Cat cat = session.get(Cat.class, id);
    session.close();
    return cat;
  }

  public void save(Cat cat) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(cat);
    transaction.commit();
    session.close();
  }

  public void update(Cat cat) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(cat);
    transaction.commit();
    session.close();
  }

  public void delete(Cat cat) {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(cat);
    transaction.commit();
    session.close();
  }
}
