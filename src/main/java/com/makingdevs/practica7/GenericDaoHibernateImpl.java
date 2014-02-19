package com.makingdevs.practica7;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.makingdevs.dao.GenericDao;

public abstract class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

  private SessionFactory sessionFactory;

  private Class<T> type = null;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void create(T newInstance) {
    sessionFactory.getCurrentSession().save(newInstance);
  }

  @SuppressWarnings("unchecked")
  @Override
  public T read(PK id) {
    return (T) sessionFactory.getCurrentSession().get(getType(), id);
  }

  @Override
  public void update(T transientObject) {
    sessionFactory.getCurrentSession().update(transientObject);
  }

  @Override
  public void delete(T persistentObject) {
    sessionFactory.getCurrentSession().delete(persistentObject);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> findAll() {
    return sessionFactory.getCurrentSession().createCriteria(getType()).list();
  }

  @Override
  public int countAll() {
    return (Integer) sessionFactory.getCurrentSession().createCriteria(getType()).setProjection(Projections.rowCount())
        .uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public Class<T> getType() {
    if (type == null) {
      Class<?> clazz = getClass();
      while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
        clazz = clazz.getSuperclass();
      }
      type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return type;
  }

}