package cn.itcast.service;


import itcast.domain.RoleInfo;
import itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {
    public abstract List<UserInfo> findAll() throws Exception;

    public abstract Integer save(UserInfo user) throws Exception;

    public abstract UserInfo findUserByName(String userName) throws Exception;

    public abstract UserInfo findUserInfoById(Integer id) throws Exception;

    public abstract List<RoleInfo> findOtherRoles(Integer id) throws Exception;

    public abstract void addRoleToUser(Integer user_id, Integer[] ids) throws Exception;

}
