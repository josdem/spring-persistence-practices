package com.makingdevs.model;

import java.util.Date;
import java.util.List;
 
public class Task {
  private Long id;
  private String description;
  private TaskStatus status;
  private Date dateCreated;
  private Date lastUpdated;
   
  private UserStory userStory;
  private List<User> participants;
  
  public Task(){ super(); }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public TaskStatus getStatus() {
    return status;
  }
  public void setStatus(TaskStatus status) {
    this.status = status;
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
  public UserStory getUserStory() {
    return userStory;
  }
  public void setUserStory(UserStory userStory) {
    this.userStory = userStory;
  }
  public List<User> getParticipants() {
    return participants;
  }
  public void setParticipants(List<User> participants) {
    this.participants = participants;
  }

}