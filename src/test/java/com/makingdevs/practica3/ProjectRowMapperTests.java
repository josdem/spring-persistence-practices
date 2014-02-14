package com.makingdevs.practica3;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.util.Assert.*;

import com.makingdevs.model.Project;
import com.makingdevs.practica2.JdbcTemplateConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JdbcTemplateConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectRowMapperTests {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void testQueryWithMapper() {
    String sql = "SELECT * FROM project";
    List<Project> projects = jdbcTemplate.query(sql, new ProjectMapper());
    assertEquals(projects.size(), 4);
    for (Project p : projects) {
      assertEquals(p.getClass(), Project.class);
      isTrue(p.getId() > 0);
    }
  }

}