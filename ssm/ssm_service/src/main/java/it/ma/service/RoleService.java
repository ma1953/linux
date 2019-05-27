package it.ma.service;

import it.ma.domain.Permission;
import it.ma.domain.Role;

import java.util.List;

public interface RoleService {
     public List<Role> findAll();

     public  void save(Role role);

     public  Role findById(String id) throws Exception;

     public  void deleteRoleById(String id);

   public    List<Permission> findPermissionByRoleId(String roleId) throws Exception;

}
