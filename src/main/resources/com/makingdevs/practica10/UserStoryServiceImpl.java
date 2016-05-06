package com.makingdevs.practica10;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makingdevs.dao.UserStoryDao;
import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;
import com.makingdevs.services.BusinessException;
import com.makingdevs.services.UserStoryServiceChecked;

@Service
@Transactional
public class UserStoryServiceImpl implements UserStoryServiceChecked {

  @Autowired
  UserStoryDao userStoryDao;

  @Override
  public void createUserStory(final UserStory userStory) {
    userStory.setDateCreated(new Date());
    userStory.setLastUpdated(new Date());
    userStoryDao.create(userStory);
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserStory> findUserStoriesByProject(final String codeName) {
    Project project = new Project();
    project.setCodeName(codeName);
    project.setId(1L);
    return userStoryDao.findAllByProject(project);
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = BusinessException.class)
  public boolean isUserStoryDone(Long userStoryId) throws BusinessException {
    throw new BusinessException("Checked exception");
  }

  @Override
  @Transactional(readOnly = true)
  public UserStory findUserStoryByIdentifier(Long userStoryId) {
    throw new UnsupportedOperationException("Runtime exception");
  }

}