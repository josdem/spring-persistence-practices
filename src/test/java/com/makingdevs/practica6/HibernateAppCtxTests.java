package com.makingdevs.practica6;

import static org.springframework.util.Assert.notNull;

import org.hibernate.SessionFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "HibernateAppCtx.xml", "../practica1/DataSourceWithNamespace.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibernateAppCtxTests {

  @Autowired
  SessionFactory sessionFactory;

  @Test
  public void test0SessionFactory() {
    notNull(sessionFactory);
  }

  @Test
  public void test1Session() {
    org.springframework.util.Assert.notNull(sessionFactory.openSession());
  }

}