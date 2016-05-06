package com.makingdevs.practica2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ImportResource(value={"classpath:/com/makingdevs/practica1/DataSourceWithNamespace.xml"})
public class JdbcTemplateConfig {

  @Autowired
  DataSource dataSource;
  
  @Bean
  public JdbcTemplate jdbcTemplate(){
    return new JdbcTemplate(dataSource);
  }
}