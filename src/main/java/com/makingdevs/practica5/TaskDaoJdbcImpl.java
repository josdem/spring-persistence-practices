package com.makingdevs.practica5;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.makingdevs.dao.TaskDao;
import com.makingdevs.model.Task;
import com.makingdevs.model.TaskStatus;
import com.makingdevs.model.User;
import com.makingdevs.model.UserStory;

@Repository
public class TaskDaoJdbcImpl extends NamedParameterJdbcDaoSupport implements TaskDao {

  @Autowired
  public TaskDaoJdbcImpl(DataSource dataSource) {
    super.setDataSource(dataSource);
  }

  private static String sqlQuery = "SELECT * FROM TASK ";

  @Override
  public void create(Task newInstance) {
    String sqlInsert = "INSERT INTO TASK(DATE_CREATED,DESCRIPTION,LAST_UPDATED,STATUS,USER_STORY_ID)"
        + " VALUES(:DATE_CREATED,:DESCRIPTION,:LAST_UPDATED,:STATUS,:USER_STORY_ID)";
    Map<String, Object> inputParameters = new HashMap<String, Object>();
    inputParameters.put("DATE_CREATED", new Date());
    inputParameters.put("LAST_UPDATED", new Date());
    inputParameters.put("DESCRIPTION", newInstance.getDescription());
    inputParameters.put("STATUS", TaskStatus.TODO.toString());
    inputParameters.put("USER_STORY_ID", newInstance.getUserStory().getId());
    getNamedParameterJdbcTemplate().update(sqlInsert, inputParameters);
    String sqlForPrimaryKey = "SELECT ID FROM TASK WHERE DESCRIPTION = :DESCRIPTION AND STATUS = :STATUS";
    Long currentId = getNamedParameterJdbcTemplate().queryForObject(sqlForPrimaryKey, inputParameters, Long.class);
    newInstance.setId(currentId);
  }

  @Override
  public Task read(Long id) {
    SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
    return getNamedParameterJdbcTemplate().queryForObject(sqlQuery + "WHERE ID = :id", namedParameters,
        new TaskMapper());
  }

  @Override
  public void update(Task transientObject) {
    String sqlUpdate = "UPDATE TASK SET DESCRIPTION = :description,"
        + "LAST_UPDATED = :lastUpdated,STATUS = :status,USER_STORY_ID = :userStory.id WHERE ID = :id";
    transientObject.setLastUpdated(new Date());
    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(transientObject);
    getNamedParameterJdbcTemplate().update(sqlUpdate, namedParameters);
  }

  @Override
  public void delete(Task persistentObject) {
    String sqlDelete = "DELETE FROM TASK WHERE ID = ?";
    getJdbcTemplate().update(sqlDelete, persistentObject.getId());
  }

  @Override
  public List<Task> findAll() {
    return getNamedParameterJdbcTemplate().query(sqlQuery, new TaskMapper());
  }

  @Override
  public int countAll() {
    String countSql = "SELECT count(*) FROM task";
    return getJdbcTemplate().queryForObject(countSql, Integer.class);
  }

  @Override
  public List<Task> findAllByDescriptionLike(String description) {
    String sqlFindAllByDescription = sqlQuery + " WHERE DESCRIPTION LIKE :DESCRIPTION";
    SqlParameterSource namedParameters = new MapSqlParameterSource("DESCRIPTION", description);
    return getNamedParameterJdbcTemplate().query(sqlFindAllByDescription, namedParameters, new TaskMapper());
  }

  @Override
  public List<Task> findAllByUserStoryAndStatusEquals(UserStory userStory, TaskStatus taskStatus) {
    // Is your turn SQL expert !!!
    return null;
  }

  @Override
  public List<Task> findAllByUser(User user) {
    // Is your turn!!!!
    return null;
  }

}
