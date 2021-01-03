package cn.itcast.service;


import itcast.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public abstract List<Permission> findAll() throws Exception;

    public abstract void save(Permission permission) throws Exception;

    public abstract void deletePermission(Integer id) throws Exception;
}
