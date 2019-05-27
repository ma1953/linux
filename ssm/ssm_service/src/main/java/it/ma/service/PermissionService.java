package it.ma.service;

import it.ma.domain.Permission;

import java.util.List;

public interface PermissionService {
  public    List<Permission>  findAll();

  public    void save(Permission permission);

  public   Permission findById(String id) throws Exception;

  public  void deleteById(String id) throws Exception;
}
