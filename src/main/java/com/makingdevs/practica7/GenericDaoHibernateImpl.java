package com.makingdevs.practica7;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.makingdevs.dao.GenericDao;

public class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
  
  @Autowired
  SessionFactory sessionFactory;
  
  private Class<T> type = null;

  @Override
  public void create(T newInstance) {
    sessionFactory.getCurrentSession().save(newInstance);
  }

  @Override
  public T read(PK id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(T transientObject) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(T persistentObject) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<T> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int countAll() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  
  @SuppressWarnings("unchecked")
  public Class<T> getType() {
    if (type == null) {
      Class<?> clazz = getClass();

      while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
        clazz = clazz.getSuperclass();
      }

      type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass())
          .getActualTypeArguments()[0];
    }
    return type;
  }
  
}