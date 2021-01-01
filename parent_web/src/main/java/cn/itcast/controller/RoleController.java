package cn.itcast.controller;

import cn.itcast.service.IRoleService;
import cn.itcast.service.impl.RoleServiceImpl;
import itcast.domain.Permission;
import itcast.domain.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<RoleInfo> roleInfos = roleService.findAll();
        mv.addObject("roleList", roleInfos);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping(value = "/save.do")
    public String save(RoleInfo roleInfo) throws Exception{
        roleService.save(roleInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping(value = "findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer id) throws Exception{
        ModelAndView mv = new ModelAndView();
        RoleInfo roleInfo = roleService.findById(id);
        mv.addObject("role", roleInfo);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping(value = "/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id", required = true) Integer id) throws Exception{
        roleService.deleteRoleById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping(value = "/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        RoleInfo roleInfo = roleService.findById(id);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(id);
        mv.addObject("role", roleInfo);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping(value = "/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId,
                                            @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        for (String permissionId:permissionIds) {
            roleService.addPermissionToRole(roleId, Integer.parseInt(permissionId));
        }
        return "redirect:findAll.do";
    }
}
