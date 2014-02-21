package com.makingdevs.practica8;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;
import com.makingdevs.services.BusinessException;
import com.makingdevs.services.UserStoryServiceChecked;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TransactionTemplateConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProgrammaticTransactionsTests {

  @Autowired
  UserStoryServiceChecked userStoryService;

  @Test
  public void test1CreateUSWithTx() {
    UserStory us = new UserStory();
    us.setDescription("As an user...I want...Beacuse...");
    us.setEffort(5);
    us.setPriority(3);
    Project p = new Project();
    p.setId(1L);
    us.setProject(p);
    userStoryService.createUserStory(us);
    Assert.assertTrue(us.getId() > 0);
    System.out.println(us.getId());
  }

  @Test
  public void test2FindUSByProjectCodeNameWithTX() {
    List<UserStory> userStories = userStoryService.findUserStoriesByProject("PROJECTNAME");
    Assert.assertTrue(userStories.size() > 0);
  }

  @Test
  public void test3FindCheckedExceptionTX() throws BusinessException {
    userStoryService.isUserStoryDone(1L);
  }

  @Test(expected = RuntimeException.class)
  public void test4FindUncheckedExceptionTX() {
    userStoryService.findUserStoryByIdentifier(1L);
  }

}
