package com.makingdevs.practica7;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.makingdevs.dao.ProjectDao;
import com.makingdevs.model.Project;

@Repository
public class ProjectDaoHibernateImpl extends GenericDaoHibernateImpl<Project, Long> implements ProjectDao {

  @Autowired
  public ProjectDaoHibernateImpl(SessionFactory sessionFactory) {
    super.setSessionFactory(sessionFactory);
  }

  @Override
  public Project findByCodename(String codename) {
    Query query = getSessionFactory().getCurrentSession().createQuery("from Project where codeName = ?");
    query.setString(0, codename);
    return (Project) query.uniqueResult();
  }

}