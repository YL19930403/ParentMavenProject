package cn.itcast.dao;

import itcast.domain.RoleInfo;
import itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface IUserDao {

    @Select("SELECT * FROM `user`")
    public abstract List<UserInfo> findAll() throws Exception;

    @Insert("INSERT INTO `user`(email, user_name, password, phone_no, status) VALUES(#{email}, #{user_name}, #{password}, #{phone_no}, #{status})")
    public Integer save(UserInfo user) throws Exception;

    @Select("SELECT * FROM `user` WHERE user_name = #{userName} ")
    @Results({
            @Result(id = true, column = "id", property = "id", jdbcType = JdbcType.BIGINT, javaType = Integer.class),
            @Result(column = "user_name", property = "user_name", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "phone_no", property = "phone_no", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER, javaType = Integer.class),
            @Result(column = "id", property = "roleInfos", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IRoleDao.findListByUserId")),
    })
    public UserInfo findUserByName(String userName) throws Exception;

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    @Results({
        @Result(id = true, column = "id", jdbcType = JdbcType.INTEGER, property = "id", javaType = Integer.class),
        @Result(column = "email", jdbcType = JdbcType.VARCHAR, property = "email", javaType = String.class),
        @Result(column = "password", jdbcType = JdbcType.VARCHAR, property = "password", javaType = String.class),
        @Result(column = "user_name", jdbcType = JdbcType.VARCHAR, property = "user_name", javaType = String.class),
        @Result(column = "phone_no", jdbcType = JdbcType.BIGINT, property = "phone_no", javaType = String.class),
        @Result(column = "status", jdbcType = JdbcType.TINYINT, property = "status", javaType = Integer.class),
        // 根据用户id去user_role表拿数据
        @Result(column = "id", property = "roleInfos", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IRoleDao.findListByUserId")),
    })
    public UserInfo findUserInfoById(Integer id) throws Exception;

    @Select("SELECT * FROM `role` WHERE id NOT IN (SELECT role_id FROM `user_role` WHERE user_id=#{id})")
    @Results({
            @Result(id = true, column = "id",property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "role_name",property = "roleName", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "role_desc",property = "roleDesc", jdbcType = JdbcType.VARCHAR, javaType = String.class),
    })
    public List<RoleInfo> findOtherRoles(Integer id) throws Exception;

    @Insert("INSERT INTO `user_role`(user_id, role_id) VALUES(#{user_id}, #{id})")
    public void addRoleToUser(@Param("user_id") Integer user_id, @Param("id") Integer id) throws Exception;
}
