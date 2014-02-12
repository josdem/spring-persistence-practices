package com.makingdevs.dao;

import com.makingdevs.model.User;

public interface UserDao extends GenericDao<User, Long> {
  User findByUsername(String username);
  // So many methods as you want...
}