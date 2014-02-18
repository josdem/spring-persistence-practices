package com.makingdevs.practica3;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.makingdevs.model.Project;

public class ProjectMapper implements RowMapper<Project> {

  @Override
  public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
    Project project = new Project();
    project.setId(rs.getLong("id"));
    project.setName(rs.getString("name"));
    project.setCodeName(rs.getString("code_name"));
    project.setDescription(rs.getString("description"));
    project.setDateCreated(rs.getDate("date_created"));
    project.setLastUpdated(rs.getDate("last_updated"));
    return project;
  }

}