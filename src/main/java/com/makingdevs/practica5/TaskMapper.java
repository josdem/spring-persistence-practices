package com.makingdevs.practica5;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.makingdevs.model.Task;
import com.makingdevs.model.TaskStatus;
import com.makingdevs.model.UserStory;

public class TaskMapper implements RowMapper<Task> {

  @Override
  public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
    Task task = new Task();
    task.setId(rs.getLong("ID"));
    task.setDateCreated(rs.getDate("DATE_CREATED"));
    task.setDateCreated(rs.getDate("LAST_UPDATED"));
    task.setDescription(rs.getString("DESCRIPTION"));
    task.setStatus(TaskStatus.valueOf(rs.getString("STATUS")));
    UserStory us = new UserStory();
    us.setId(rs.getLong("USER_STORY_ID"));
    task.setUserStory(us);
    return task;
  }

}