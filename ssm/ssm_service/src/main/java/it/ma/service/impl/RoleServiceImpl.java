package it.ma.service.impl;

import it.ma.dao.RolesDao;
import it.ma.domain.Permission;
import it.ma.domain.Role;
import it.ma.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    @Qualifier("rolesDao")
    private RolesDao rolesDao;
    @Override
    public List<Role> findAll() {
     return rolesDao.findAll();
    }

    @Override
    public void save(Role role) {
        rolesDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return rolesDao.findById(id);
    }

    @Override
    public void deleteRoleById(String id) {
        rolesDao.deleteFromUsersRoleByRoleId(id);
        rolesDao.deleteRoleById(id);
        rolesDao.deleteFromPermissionRoleByRoleId(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String roleId) throws Exception {
        return rolesDao.findPermissionByRoleId(roleId);
    }


}
