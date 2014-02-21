package com.makingdevs.practica8;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.makingdevs.dao.UserStoryDao;
import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;
import com.makingdevs.services.BusinessException;
import com.makingdevs.services.UserStoryServiceChecked;

@Service
public class UserStoryServiceImpl implements UserStoryServiceChecked {

  @Autowired
  TransactionTemplate transactionTemplate;

  @Autowired
  UserStoryDao userStoryDao;

  @Override
  public void createUserStory(final UserStory userStory) {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        userStory.setDateCreated(new Date());
        userStory.setLastUpdated(new Date());
        userStoryDao.create(userStory);
      }
    });
  }

  @Override
  public List<UserStory> findUserStoriesByProject(final String codeName) {
    transactionTemplate.setReadOnly(true);
    List<UserStory> userStories = transactionTemplate.execute(new TransactionCallback<List<UserStory>>() {
      @Override
      public List<UserStory> doInTransaction(TransactionStatus status) {
        Project project = new Project();
        project.setCodeName(codeName);
        project.setId(1L);
        // TODO: Find project by codeName must be implemented...
        return userStoryDao.findAllByProject(project);
      }
    });
    transactionTemplate.setReadOnly(false);
    return userStories;
  }

  @Override
  public boolean isUserStoryDone(Long userStoryId) throws BusinessException {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        try {
          throw new BusinessException("CheckedExcpetion");
        } catch (BusinessException e) {
          e.printStackTrace();
        }
      }
    });
    return false;
  }

  @Override
  public UserStory findUserStoryByIdentifier(Long userStoryId) {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        throw new UnsupportedOperationException("Runtime exception");
      }
    });
    return null;
  }

}
