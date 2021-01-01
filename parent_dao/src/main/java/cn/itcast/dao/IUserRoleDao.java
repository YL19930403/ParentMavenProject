package cn.itcast.dao;

import itcast.domain.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserRoleDao {

    @Select("SELECT id, user_id as userId, role_id as roleId  FROM `user_role` WHERE user_id = #{userId}")
    public abstract List<UserRole> findByUserId(Integer userId) throws Exception;
}
