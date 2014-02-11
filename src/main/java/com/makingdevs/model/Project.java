package com.makingdevs.model;

import java.util.Date;
import java.util.List;
 
public class Project {
  private Long id;
  private String name;
  private String codeName;
  private String description;
  private Date dateCreated;
  private Date lastUpdated;
   
  private List<UserStory> userStories;
  private List<User> participants;
  
  public Project(){ super(); }

  public Project(Long id, String name, String codeName, String description) {
    super();
    this.id = id;
    this.name = name;
    this.codeName = codeName;
    this.description = description;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Date getDateCreated() {
    return dateCreated;
  }
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }
  public Date getLastUpdated() {
    return lastUpdated;
  }
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
  public List<UserStory> getUserStories() {
    return userStories;
  }
  public void setUserStories(List<UserStory> userStories) {
    this.userStories = userStories;
  }
  public List<User> getParticipants() {
    return participants;
  }
  public void setParticipants(List<User> participants) {
    this.participants = participants;
  }
   
  
}