package cn.itcast.service;

import itcast.domain.Permission;
import itcast.domain.RoleInfo;

import java.util.List;

public interface IRoleService {

    public abstract List<RoleInfo> findAll() throws Exception;

    public abstract void save(RoleInfo roleInfo) throws Exception;

    public abstract RoleInfo findById(Integer id) throws Exception;

    public abstract void deleteRoleById(Integer id) throws Exception;

    public abstract List<Permission> findOtherPermissions(Integer id) throws Exception;

    public abstract void addPermissionToRole(Integer roleId, Integer permissionId) throws Exception;
}
