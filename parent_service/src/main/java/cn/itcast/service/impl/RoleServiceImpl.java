package cn.itcast.service.impl;


import cn.itcast.dao.IRoleDao;
import cn.itcast.service.IRoleService;
import itcast.domain.Permission;
import itcast.domain.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<RoleInfo> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(RoleInfo roleInfo) throws Exception {
        roleDao.save(roleInfo);
    }

    @Override
    public RoleInfo findById(Integer id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleById(Integer id) throws Exception {
        roleDao.deleteRoleById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer id) throws Exception {
        return roleDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer permissionId) throws Exception {
        roleDao.addPermissionToRole(roleId, permissionId);
    }
}
