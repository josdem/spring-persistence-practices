package com.makingdevs.practica10;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource(value={"classpath:/com/makingdevs/practica1/DataSourceWithNamespace.xml"})
@ComponentScan(basePackages={"com.makingdevs.practica4","com.makingdevs.practica10"})
@EnableTransactionManagement
public class AnnotatedTransactionsConfig {
  
  @Autowired
  DataSource dataSource;

  @Bean
  public DataSourceTransactionManager transactionManager(){
    return new DataSourceTransactionManager(dataSource);
  }

}