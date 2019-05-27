package it.ma.service.impl;

import it.ma.dao.UserDao;
import it.ma.domain.Role;
import it.ma.domain.Users;
import it.ma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl  implements UserService{
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=null;
        try {
            users = userDao.findByName(username);
            System.out.println(users.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user=new User(users.getUsername(),users.getPassword(),users.getStatus()==0?false:true,true,
                true,
                true,getAuthority(users.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(Role role :roles){
           list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        return list;
    }

    @Override
    public List<Users> findAll() throws Exception {
        List<Users> users = userDao.findAll();
        return users;
    }

    @Override
    public void save(Users users) throws Exception {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
          userDao.save(users);
    }

    @Override
    public Users findById(String userId) throws Exception {
        Users users = userDao.findById(userId);
        return users;
    }

    @Override
    public List<Role> findCanAddAllRoleByUserId(String id) throws Exception {
        return userDao.findCanAddAllRoleByUserId(id);
    }

    @Override
    public void addRolesToUsersByUserIdAndRoleId(String usersId, String[] roleIds) throws Exception {
            for(int i=0;i<roleIds.length;i++) {
                userDao.addRoleToUsersByUserIdAndRoleId(usersId,roleIds[i]);
            }
    }
}
