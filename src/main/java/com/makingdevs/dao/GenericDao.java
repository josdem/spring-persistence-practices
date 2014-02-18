package com.makingdevs.dao;

import java.io.Serializable;
import java.util.List;

/** The Generic DAO for all persistence interfaces */
public interface GenericDao<T, PK extends Serializable> {

  /** Persist the newInstance object into database */
  void create(T newInstance);

  /**
   * Retrieve an object that was previously persisted to the database using the
   * indicated id as primary key
   */
  T read(PK id);

  /** Save changes made to a persistent object. */
  void update(T transientObject);

  /** Remove an object from persistent storage in the database */
  void delete(T persistentObject);

  /** Retrieves a list of instances */
  List<T> findAll();

  /** Count the current instances persisted */
  int countAll();
}