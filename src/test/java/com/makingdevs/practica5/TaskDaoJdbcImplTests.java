package com.makingdevs.practica5;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notEmpty;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevs.dao.TaskDao;
import com.makingdevs.model.Task;
import com.makingdevs.model.UserStory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TaskDaoConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskDaoJdbcImplTests {

  @Autowired
  TaskDao taskDao;

  private static long taskId;

  @Test
  public void test0FindAll() {
    List<Task> tasks = taskDao.findAll();
    notEmpty(tasks);
  }

  @Test
  public void test1CreateTask() {
    Task task = new Task();
    task.setDescription("A new task");
    UserStory userStory = new UserStory();
    userStory.setId(1L);
    task.setUserStory(userStory);
    taskDao.create(task);
    isTrue(task.getId() > 0);
    taskId = task.getId();
  }

  @Test
  public void test2QueryTask() {
    Task task = taskDao.read(taskId);
    Assert.assertNotNull(task);
    isTrue(task.getDescription().equals("A new task"));
  }

  // Your responsability is to test!!!!

}