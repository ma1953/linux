package it.ma.dao;

import it.ma.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionDao {
    @Select("select *from permission")
    public    List<Permission> findAll();

    @Select("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public  void save(Permission permission);

    @Select("select * from permission where id in(select permissionId  from  role_permission where roleId=#{roleId})")
    public  List<Permission> findPermisionsByRoleId(String roleId) throws Exception;

    @Select("select * from permission where id=#{id}")
      public   Permission findById(String id);

    @Delete("delete from permission where id=#{id}")
   public   void deleteById(String id) throws Exception;

    @Delete("delete from role_permission  where permissionId=#{id}")
    public  void  deleteRolePermissionByPermissionId(String id) throws Exception;
}
