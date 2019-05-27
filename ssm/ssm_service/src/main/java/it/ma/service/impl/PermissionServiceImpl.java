package it.ma.service.impl;

import it.ma.dao.PermissionDao;
import it.ma.domain.Permission;
import it.ma.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl  implements PermissionService{
    @Autowired
    @Qualifier("permissionDao")
     public PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {

        return   permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return   permissionDao.findById(id);

    }

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteRolePermissionByPermissionId(id);
        permissionDao.deleteById(id);

    }
}
