package com.makingdevs.practica12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "LiquibaseAppCtx.xml" })
public class RefactorDatabaseTests {

  @Autowired
  ApplicationContext applicationContext;
  
  @Test
  public void testRefactor(){
    Assert.notNull(applicationContext);
  }

}