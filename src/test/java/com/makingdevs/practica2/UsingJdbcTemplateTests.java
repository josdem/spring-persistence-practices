package com.makingdevs.practica2;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JdbcTemplateConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsingJdbcTemplateTests {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void test1CountWithJdbcTemplate() {
    int rowCount = jdbcTemplate.queryForObject("SELECT count(*) FROM user", Integer.class);
    assertEquals(7, rowCount);
  }

  @Test
  public void test2CountBindingVariableWithJdbcTemplate() {
    int rowCount = jdbcTemplate.queryForObject("SELECT count(*) FROM user WHERE username = ?", Integer.class,
        "makingdevs");
    assertEquals(1, rowCount);
  }

  @Test
  public void test3QueryStringWithJdbcTemplate() {
    String projectName = jdbcTemplate.queryForObject("SELECT code_name FROM project WHERE id = ?", new Object[] { 4L },
        String.class);
    assertEquals("AGILE-TASKBOARD", projectName);
  }

  @Test
  public void test4InsertWithJdbcTemplate() {
    int rowCount = jdbcTemplate.update(
        "INSERT INTO project(CODE_NAME,DESCRIPTION,NAME,DATE_CREATED,LAST_UPDATED) values(?,?,?,?,?)", "PROJECT",
        "This is a new project", "New project", new Date(), new Date());
    assertEquals(1, rowCount); // Why this is 1?
    String projectDescription = jdbcTemplate.queryForObject("SELECT description FROM project WHERE CODE_NAME = ?", new Object[] { "PROJECT" },
        String.class);
    assertEquals(projectDescription, "This is a new project");
  }
  
  @Test
  public void test5UpdateWithJdbcTemplate() {
    int rowCount = jdbcTemplate.update(
        "UPDATE project SET DESCRIPTION = ?,NAME = ?,LAST_UPDATED = ? WHERE CODE_NAME = ?", "The project is updated",
        "Project Updated", new Date(), "PROJECT");
    assertEquals(1, rowCount);
  }
  
  @Test(expected=DataAccessException.class)
  public void test6DeleteWithJdbcTemplate() {
    int rowCount = jdbcTemplate.update(
        "DELETE FROM project WHERE CODE_NAME = ?", "PROJECT");
    assertEquals(1, rowCount);
    String projectDescription = jdbcTemplate.queryForObject("SELECT description FROM project WHERE CODE_NAME = ?", new Object[] { "PROJECT" },
        String.class);
  }
  
  @Test
  public void test7CreateTempTableWithJdbcTemplate(){
    jdbcTemplate.execute("CREATE TABLE TEMP(ID INTEGER, NAME VARCHAR(100))");
  }

}