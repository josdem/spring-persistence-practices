package com.makingdevs.practica4;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;

public class UserStoryMapper implements RowMapper<UserStory> {

  @Override
  public UserStory mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserStory userStory = new UserStory();
    userStory.setId(rs.getLong("ID"));
    userStory.setDateCreated(rs.getDate("DATE_CREATED"));
    userStory.setLastUpdated(rs.getDate("LAST_UPDATED"));
    userStory.setEffort(rs.getInt("EFFORT"));
    userStory.setPriority(rs.getInt("PRIORITY"));
    userStory.setDescription(rs.getString("DESCRIPTION"));
    Project project = new Project();
    project.setId(rs.getLong("PROJECT_ID"));
    userStory.setProject(project);
    return userStory;
  }

}