package cn.itcast.dao;


import itcast.domain.Permission;
import itcast.domain.RoleInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    @Select("SELECT id, role_name as roleName, role_desc as roleDesc FROM `role`" )
    public abstract List<RoleInfo> findAll() throws Exception;

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("SELECT * FROM `role` WHERE id in (SELECT role_id FROM `user_role` WHERE user_id = #{userId})" )
    @Results({
            @Result(id = true, column = "id", property = "id", jdbcType = JdbcType.INTEGER, javaType = Integer.class),
            @Result(column = "role_name", property = "roleName", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "role_desc", property = "roleDesc", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IPermissionDao.findListByRoleId")),
    })
    public abstract List<RoleInfo> findListByUserId(Integer userId) throws Exception;

    @Insert("INSERT INTO `role`(role_name, role_desc)  VALUES(#{roleName}, #{roleDesc})")
    public abstract void save(RoleInfo roleInfo) throws Exception;

    @Select("SELECT * FROM `role` WHERE id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id", jdbcType = JdbcType.INTEGER, javaType = Integer.class),
            @Result(column = "roleName", property = "roleName", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "roleDesc", property = "roleDesc", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IPermissionDao.findListByRoleId")),
    })
    public abstract RoleInfo findById(Integer id) throws Exception;

    @Delete("DELETE FROM `role` WHERE id = #{id}")
    public abstract void deleteRoleById(Integer id) throws Exception;

    @Select("SELECT id, permission_name as permissionName, url FROM `permission` WHERE id in (SELECT permission_id FROM `role_permission` WHERE role_id = #{id})")
    public abstract List<Permission> findOtherPermissions(Integer id) throws Exception;

    @Insert("insert into `role_permission`(role_id, permission_id) values(#{roleId},#{permissionId})")
    public abstract void addPermissionToRole(Integer roleId, Integer permissionId) throws Exception;
}
