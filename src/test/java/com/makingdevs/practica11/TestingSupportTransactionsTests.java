package com.makingdevs.practica11;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;
import com.makingdevs.services.BusinessException;
import com.makingdevs.services.UserStoryServiceChecked;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../practica10/TransactionsAnnotationsAppCtx.xml" })
// @ContextConfiguration(classes = { AnnotatedTransactionsConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestingSupportTransactionsTests {

  private Log log = LogFactory.getLog(TestingSupportTransactionsTests.class);

  @Autowired
  UserStoryServiceChecked userStoryService;

  @BeforeTransaction
  public void verifyInitialDatabaseState() {
    log.debug("logic to verify the initial state before a transaction is started");
  }

  @Before
  public void setUpTestDataWithinTransaction() {
    log.debug("set up test data within the transaction");
  }

  @After
  public void tearDownWithinTransaction() {
    log.debug("execute \"tear down\" logic within the transaction");
  }

  @AfterTransaction
  public void verifyFinalDatabaseState() {
    log.debug("logic to verify the final state after transaction has rolled back");
  }

  @Test
  // overrides the class-level defaultRollback setting
  @Rollback(true)
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
  @Rollback(true)
  public void test2FindUSByProjectCodeNameWithTX() {
    List<UserStory> userStories = userStoryService.findUserStoriesByProject("PROJECTNAME");
    Assert.assertTrue(userStories.size() > 0);
  }

  @Test(expected = BusinessException.class)
  public void test3FindCheckedExceptionTX() throws BusinessException {
    userStoryService.isUserStoryDone(1L);
  }

  @Test(expected = RuntimeException.class)
  public void test4FindUncheckedExceptionTX() {
    userStoryService.findUserStoryByIdentifier(1L);
  }

}
