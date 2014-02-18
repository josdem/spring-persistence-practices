package com.makingdevs.practica4;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.makingdevs.dao.UserStoryDao;
import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;

@Repository
public class UserStoryDaoJdbcImpl extends JdbcDaoSupport implements UserStoryDao {
  
  @Autowired
  public UserStoryDaoJdbcImpl(DataSource dataSource){
    super.setDataSource(dataSource);
  }

  private static String sqlQuery = "SELECT * FROM user_story";

  @Override
  public void create(UserStory newInstance) {
    String sqlInsert = "INSERT INTO user_story(DATE_CREATED,DESCRIPTION,EFFORT,LAST_UPDATED,PRIORITY,PROJECT_ID) "
        + "VALUES(?,?,?,?,?,?);";
    getJdbcTemplate().update(sqlInsert, new Date(), newInstance.getDescription(), newInstance.getEffort(), new Date(),
        newInstance.getPriority(), newInstance.getProject().getId());
    Long userStoryId = getJdbcTemplate().queryForObject(
        "SELECT id FROM user_story WHERE DESCRIPTION = ? AND PROJECT_ID = ?",
        new Object[] { newInstance.getDescription(), newInstance.getProject().getId() }, Long.class);
    newInstance.setId(userStoryId);
  }

  @Override
  public UserStory read(Long id) {
    return getJdbcTemplate().queryForObject(sqlQuery + " WHERE ID = ?", new Object[] { id }, new UserStoryMapper());
  }

  @Override
  public void update(UserStory transientObject) {
    String sqlUpdate = "UPDATE user_story SET DESCRIPTION = ?,EFFORT = ?,LAST_UPDATED = ?,PRIORITY = ?,PROJECT_ID = ? WHERE ID = ?";
    getJdbcTemplate().update(sqlUpdate, transientObject.getDescription(), transientObject.getEffort(), new Date(),
        transientObject.getPriority(), transientObject.getProject().getId(), transientObject.getId());
  }

  @Override
  public void delete(UserStory persistentObject) {
    String sqlDelete = "DELETE FROM user_story WHERE ID = ?";
    getJdbcTemplate().update(sqlDelete, persistentObject.getId());
  }

  @Override
  public List<UserStory> findAll() {
    return getJdbcTemplate().query(sqlQuery, new UserStoryMapper());
  }

  @Override
  public int countAll() {
    String countQuery = "SELECT count(*) FROM user_story";
    return getJdbcTemplate().queryForObject(countQuery, Integer.class);
  }

  @Override
  public List<UserStory> findAllByEffortBetween(Integer lowValue, Integer maxValue) {
    String findAllByEffortBetweenSql = sqlQuery + " WHERE EFFORT BETWEEN ? AND ?";
    return getJdbcTemplate().query(findAllByEffortBetweenSql, new Object[] { lowValue, maxValue }, new UserStoryMapper());
  }

  @Override
  public List<UserStory> findAllByPriorityBetween(Integer lowValue, Integer maxValue) {
    String findAllByPriorityBetweenSql = sqlQuery + " WHERE PRIORITY BETWEEN ? AND ?";
    return getJdbcTemplate().query(findAllByPriorityBetweenSql, new Object[] { lowValue, maxValue }, new UserStoryMapper());
  }

  @Override
  public List<UserStory> findAllByProject(Project project) {
    String findByProjectIdSql = "SELECT * FROM user_story us INNER JOIN project p ON p.id = us.project_id WHERE us.project_id = ?";
    return getJdbcTemplate().query(findByProjectIdSql, new Object[] { project.getId() }, new UserStoryMapper());
  }

}
