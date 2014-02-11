package com.makingdevs.model;

import java.util.Date;

public class User {
  private Long id;
  private String username;
  private boolean isEnabled;
  private Date dateCreated;
  private Date lastUpdated;
  
  public User(){ super(); }
  
  public User(Long id, String username, boolean isEnabled) {
    super();
    this.id = id;
    this.username = username;
    this.isEnabled = isEnabled;
  }
  public boolean isEnabled() {
    return isEnabled;
  }
  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
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

}