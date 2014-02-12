package com.makingdevs.dao;

import java.util.List;

import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;

public interface UserStoryDao extends GenericDao<UserStory, Long> {
  List<UserStory> findAllByEffortBetween(Integer lowValue, Integer maxValue);
  List<UserStory> findAllByPriorityBetween(Integer lowValue, Integer maxValue);
  List<UserStory> findAllByProject(Project project);
}