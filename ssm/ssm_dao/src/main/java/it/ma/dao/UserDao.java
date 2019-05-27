package it.ma.dao;

import it.ma.domain.Role;
import it.ma.domain.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao {
    @Select("select * from users where username=#{userName}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = "it.ma.dao.RolesDao.findRoleByUserId"))
    })
    public Users findByName(String userName) throws Exception;

    @Select("select *from users")
    public  List<Users> findAll() throws Exception;
    @Select("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public  void  save(Users users) throws Exception;

    @Select("select *from users where id=#{userId}")
      @Results({
              @Result(id = true,column = "id",property = "id"),
              @Result(column = "username",property = "username"),
              @Result(column = "email",property = "email"),
              @Result(column = "password",property = "password"),
              @Result(column = "phoneNum",property = "phoneNum"),
              @Result(column = "status",property = "status"),
              @Result(column = "id",property = "roles",javaType = List.class,many = @Many(select = "it.ma.dao.RolesDao.findRoleByUserId"))
      })
    public  Users findById(String userId) throws Exception;


    @Select("select  * from role where id not in (select roleId from users_role where userId=#{id} )")
   public   List<Role> findCanAddAllRoleByUserId(String id);

      @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
  public    void addRoleToUsersByUserIdAndRoleId(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
