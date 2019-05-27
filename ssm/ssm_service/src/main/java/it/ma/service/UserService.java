package it.ma.service;

import it.ma.domain.Role;
import it.ma.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {
    public List<Users> findAll() throws  Exception;

    public  void  save(Users users) throws Exception;

    public  Users findById(String userId) throws  Exception;

   public  List<Role> findCanAddAllRoleByUserId(String id) throws  Exception;

   public  void addRolesToUsersByUserIdAndRoleId(String usersId, String[] roleIds) throws  Exception;
}
