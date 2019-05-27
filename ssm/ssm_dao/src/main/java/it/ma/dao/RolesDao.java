package it.ma.dao;

import it.ma.domain.Permission;
import it.ma.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rolesDao")
public interface RolesDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property ="roleName", column ="roleName"),
            @Result(property ="roleDesc", column ="roleDesc"),
            @Result(property ="permissions", column ="id" ,javaType = java.util.List.class,many = @Many(select = "it.ma.dao.PermissionDao.findPermisionsByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;


     @Select("select * from role where id=#{id}")
     @Results({
             @Result(id = true,column = "id",property = "id"),
             @Result(property = "roleName",column = "roleName"),
             @Result(property = "roleDesc",column = "roleDesc"),
             @Result(column = "id",property = "permissions" ,many = @Many(select = "it.ma.dao.PermissionDao.findPermisionsByRoleId"))

     })
    public Role findById(String id) throws Exception;


    @Select("select * from role")
      public  List<Role> findAll();
      @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
      public  void save(Role role);

      @Delete("delete from users_role where roleId=#{id}")
      public     void deleteFromUsersRoleByRoleId(String id);
      @Delete("delete  from role where id=#{id}")
      public     void deleteRoleById(String id);
     @Delete("delete from role_permission where roleId=#{id}")
      public     void deleteFromPermissionRoleByRoleId(String id);

     @Select("select * from permission where id not in(select  permissionId from  role_permission  where roleId=#{roleId})")
      public    List<Permission> findPermissionByRoleId(String roleId);
}
