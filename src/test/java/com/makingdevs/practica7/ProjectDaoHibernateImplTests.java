package com.makingdevs.practica7;

import static org.springframework.util.Assert.notNull;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import com.makingdevs.dao.ProjectDao;
import com.makingdevs.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "HibernateAppCtx.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectDaoHibernateImplTests {

  @Autowired
  ProjectDao projectDao;
  @Autowired
  DataSource dataSource;
  
  private static Long projectId;

  @Test
  public void test0ProjectDao() {
    notNull(projectDao);
    notNull(dataSource);
  }

  @Test
  public void test1CreateProject() {
    Project project = new Project();
    project.setName("New Project");
    project.setCodeName("NEWPROJECT");
    project.setDescription("This is a new project");
    project.setDateCreated(new Date());
    project.setLastUpdated(new Date());
    projectDao.create(project);
    Assert.isTrue(project.getId() > 0);
    projectId = project.getId();
  }
  
  @Test
  public void test2ReadProject(){
    Project project = projectDao.read(projectId);
    Assert.isTrue(project.getId() > 0);
    assertEquals("New Project", project.getName());
    assertEquals("NEWPROJECT", project.getCodeName());
  }
  
  @Test
  public void test3UpdateProject(){
    Project project = projectDao.read(projectId);
    String originalCodeName = project.getCodeName();
    project.setCodeName("PROJECTUPDATED");
    project.setName("Project updated");
    projectDao.update(project);
    Project projectUpdated = projectDao.read(projectId);
    assertNotEquals(originalCodeName, projectUpdated.getCodeName());
  }
  
  @Test 
  public void test4FindProjectByCodeName(){
    Project project = projectDao.findByCodename("PROJECTUPDATED");
    assertEquals("PROJECTUPDATED", project.getCodeName());
  }
  
  @Test 
  public void test5DeleteProject(){
    Project project = projectDao.read(projectId);
    projectDao.delete(project);
    Project projectDeleted = projectDao.read(projectId);
    assertNull(projectDeleted);
  }

}