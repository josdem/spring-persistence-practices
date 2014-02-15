package com.makingdevs.practica5;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.makingdevs.dao.TaskDao;

@Configuration
@ImportResource(value={"classpath:/com/makingdevs/practica1/DataSourceWithNamespace.xml"})
public class TaskDaoConfig {

  @Autowired
  DataSource dataSource;
  
  @Bean
  public TaskDao taskDado(){
    return new TaskDaoJdbcImpl(dataSource);
  }
}
