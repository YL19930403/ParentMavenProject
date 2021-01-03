package cn.itcast.dao;

import itcast.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    @Select("SELECT id, permission_name as permissionName, url FROM `permission` WHERE id in (SELECT permission_id FROM `role_permission` WHERE role_id = #{roleId})")
    public abstract List<Permission> findListByRoleId(Integer roleId) throws Exception;

    @Select("SELECT id, permission_name as permissionName, url FROM `permission`")
    public abstract List<Permission> findAll() throws Exception;

    @Insert("INSERT INTO `permission`(permission_name, url) VALUES(#{permission_name}, #{url})")
    public abstract void save(Permission permission) throws Exception;

    @Delete("DELETE FROM `permission` WHERE id = #{id}")
    public abstract void deletePermission(Integer id) throws Exception;
}
