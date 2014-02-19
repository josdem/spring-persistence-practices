package com.makingdevs.practica7;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.makingdevs.dao.ProjectDao;

//@Configuration
public class HibernateConfiguration {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    builder.addScript("classpath:/com/makingdevs/scripts/project.sql");
    builder.addScript("classpath:/com/makingdevs/scripts/user_story.sql");
    builder.addScript("classpath:/com/makingdevs/scripts/task.sql");
    builder.addScript("classpath:/com/makingdevs/scripts/user.sql");
    builder.addScript("classpath:/com/makingdevs/scripts/constraints.sql");
    return builder.setType(EmbeddedDatabaseType.H2).build();
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
    localSessionFactory.setDataSource(dataSource());
    localSessionFactory.setMappingResources("com/makingdevs/model/Project.hbm.xml",
        "com/makingdevs/model/UserStory.hbm.xml", "com/makingdevs/model/Task.hbm.xml",
        "com/makingdevs/model/User.hbm.xml");
    localSessionFactory.getHibernateProperties().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    return localSessionFactory;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public ProjectDao projectDao() {
    return new ProjectDaoHibernateImpl(sessionFactory().getObject());
  }
  
  @Bean
  public HibernateTransactionManager transactionManager(){
    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory().getObject());
    return transactionManager;
  }
}