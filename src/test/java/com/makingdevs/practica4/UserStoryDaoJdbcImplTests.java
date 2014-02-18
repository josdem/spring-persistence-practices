package com.makingdevs.practica4;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevs.dao.UserStoryDao;
import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;
import static org.springframework.util.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "FirstDaoAppCtx.xml", "../practica1/DataSourceWithNamespace.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserStoryDaoJdbcImplTests {

  @Autowired
  UserStoryDao userStoryDao;

  private static long userStoryId;

  @Test
  public void test0FindAll() {
    List<UserStory> userStories = userStoryDao.findAll();
    notEmpty(userStories);
  }

  @Test
  public void test1CreateUserStory() {
    UserStory us = new UserStory();
    us.setEffort(3);
    us.setPriority(1);
    us.setDescription("As user... I want... In order to...");
    Project p = new Project();
    p.setId(1L);
    us.setProject(p);
    userStoryDao.create(us);
    isTrue(us.getId() > 0);
    userStoryId = us.getId(); 
  }

  @Test
  public void test2QueryUserStory() {
    UserStory userStory = userStoryDao.read(userStoryId);
    isTrue(3 == userStory.getEffort());
    isTrue(1 == userStory.getPriority());
    Assert.assertEquals("As user... I want... In order to...", userStory.getDescription());
    Assert.assertNotNull(userStory.getProject());
    isTrue(userStory.getProject().getId() == 1);
  }

  @Test
  public void test3UpdateUserStory() {
    UserStory userStory = userStoryDao.read(userStoryId);
    String oldDescription = userStory.getDescription();
    userStory.setDescription("As admin... I wish... Because...");
    userStory.setEffort(5);
    userStory.setPriority(4);
    userStoryDao.update(userStory);
    UserStory userStoryUpdated = userStoryDao.read(userStoryId);
    isTrue(5 == userStoryUpdated.getEffort());
    isTrue(4 == userStoryUpdated.getPriority());
    Assert.assertEquals("As admin... I wish... Because...", userStoryUpdated.getDescription());
    Assert.assertNotEquals(oldDescription, userStoryUpdated.getDescription());
  }

  @Test(expected = DataAccessException.class)
  public void test4DeleteUserStory() {
    UserStory userStory = userStoryDao.read(userStoryId);
    userStoryDao.delete(userStory);
    userStoryDao.read(userStoryId);
  }

  @Test
  public void test5FindByEffort() {
    List<UserStory> userStories = userStoryDao.findAllByEffortBetween(1, 3);
    for (UserStory us : userStories) {
      isTrue(us.getEffort() >= 1 && us.getEffort() <= 3);
    }
  }

}