package cn.itcast.controller;

import cn.itcast.service.IUserService;
import cn.itcast.service.impl.UserServiceImpl;
import itcast.domain.RoleInfo;
import itcast.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping(value = "save.do")
    public String save(UserInfo user) throws Exception
    {
        Integer result = userService.save(user);
        if (result > 0){
            return "user-list";
        }
        return "error";
    }

    @RequestMapping(value = "findUserInfoById.do")
    public ModelAndView findUserInfoById(@RequestParam(name = "id", required = true) Integer id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findUserInfoById(id);

        mv.addObject("userInfo", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    ///查询用户以及用户可以添加的角色
    @RequestMapping(value = "/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "user_id", required = true) Integer user_id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findUserInfoById(user_id);
        if (userInfo == null){
            mv.setViewName("error");
            return mv;
        }

        //2.根据用户id查询可以添加的角色
        List<RoleInfo> roleInfos = userService.findOtherRoles(user_id);
        mv.addObject("userInfo", userInfo);
        mv.addObject("roleInfos", roleInfos);
        mv.setViewName("user-role-add");
        return mv;
    }

}
