package com.makingdevs.practica8;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@ImportResource(value={"classpath:/com/makingdevs/practica1/DataSourceWithNamespace.xml"})
// Look ma!, I'm reusing the DataSource and the JdbcTemplate
@ComponentScan(basePackages={"com.makingdevs.practica4","com.makingdevs.practica8"})
public class TransactionTemplateConfig {
  
  @Autowired
  DataSource dataSource;

  @Bean
  public DataSourceTransactionManager transactionManager(){
    return new DataSourceTransactionManager(dataSource);
  }
  
  // Look ma!, I'm declaring the Transaction Template
  @Bean
  public TransactionTemplate transactionTemplate(){
    return new TransactionTemplate(transactionManager());
  }
}