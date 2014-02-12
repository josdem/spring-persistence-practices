package com.makingdevs.dao;

import com.makingdevs.model.Project;

public interface ProjectDao extends GenericDao<Project, Long> {
  Project findByCodename(String codename);
}