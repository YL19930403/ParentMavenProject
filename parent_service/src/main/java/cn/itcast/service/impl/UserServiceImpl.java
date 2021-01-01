package cn.itcast.service.impl;

import cn.itcast.dao.IUserDao;
import cn.itcast.service.IUserService;
import itcast.domain.RoleInfo;
import itcast.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("myUserService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

//    todo
//    @Autowired
//    private BCryptPasswordEncoder bCryptPass;

    private BCryptPasswordEncoder bCryptPass = new BCryptPasswordEncoder();

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public Integer save(UserInfo user) throws Exception {
        // 对密码进行加密
        user.setPassword(bCryptPass.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public UserInfo findUserByName(String userName) throws Exception {
        return userDao.findUserByName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findUserByName(s);
        }catch (Exception e){
            e.printStackTrace();
        }

        //处理自己的用户对象封装成UserDetails
        User user = new User(userInfo.getUser_name(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true,true, true, getAuthority(userInfo.getRoleInfos()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<RoleInfo> roleInfos){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for (RoleInfo role:roleInfos) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        }
        return simpleGrantedAuthorities;
    }

    @Override
    public UserInfo findUserInfoById(Integer id) throws Exception {
        return userDao.findUserInfoById(id);
    }

    @Override
    public List<RoleInfo> findOtherRoles(Integer id) throws Exception {
        return userDao.findOtherRoles(id);
    }
}
