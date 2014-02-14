package com.makingdevs.practica2;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"NamedJdbcTemplateAppCtx.xml","../practica1/DataSourceWithNamespace.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsingNamedJdbcTemplateTests {

  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Test
  public void test1CountWithJdbcTemplate() {
    // Easy way!
    Map<String,Object> namedParameters = new HashMap<String,Object>();
    int rowCount = jdbcTemplate.queryForObject("SELECT count(*) FROM user", namedParameters, Integer.class);
    assertEquals(7, rowCount);
  }

  @Test
  public void test2CountBindingVariableWithJdbcTemplate() {
    String sql = "SELECT count(*) FROM user WHERE username = :username";
    // Using Spring parameters
    SqlParameterSource namedParameters = new MapSqlParameterSource("username", "makingdevs");
    int rowCount = jdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    assertEquals(1, rowCount);
  }

  @Test
  public void test3QueryStringWithJdbcTemplate() {
    String sql = "SELECT code_name FROM project WHERE id = :id";
    Map<String, String> namedParameters = Collections.singletonMap("id", "4");
    String projectName = jdbcTemplate.queryForObject(sql, namedParameters, String.class);
    assertEquals("AGILE-TASKBOARD", projectName);
  }

}