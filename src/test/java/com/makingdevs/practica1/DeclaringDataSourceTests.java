package com.makingdevs.practica1;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "DataSourceWithNamespace.xml" })
public class DeclaringDataSourceTests {
  
  @Autowired
  DataSource dataSource;

  @Test
  public void test() throws SQLException {
    Assert.notNull(dataSource);
    Assert.notNull(dataSource.getConnection());
  }

}