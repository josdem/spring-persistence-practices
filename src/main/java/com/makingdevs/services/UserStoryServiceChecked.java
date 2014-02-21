package com.makingdevs.services;

import java.util.List;

import com.makingdevs.model.UserStory;

public interface UserStoryServiceChecked {
  void createUserStory(UserStory userStory);
  List<UserStory> findUserStoriesByProject(String codeName);
  boolean isUserStoryDone(Long userStoryId) throws BusinessException;
  UserStory findUserStoryByIdentifier(Long userStoryId);
}