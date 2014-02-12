package com.makingdevs.dao;

import java.util.List;

import com.makingdevs.model.Task;
import com.makingdevs.model.TaskStatus;
import com.makingdevs.model.User;
import com.makingdevs.model.UserStory;

public interface TaskDao extends GenericDao<Task, Long> {
  List<Task> findAllByDescriptionLike(String description);
  List<Task> findAllByUserStoryAndStatusEquals(UserStory userStory, TaskStatus taskStatus);
  List<Task> findAllByUser(User user);
}