package cn.itcast.factory;

import cn.itcast.service.IRoleService;
import cn.itcast.service.impl.RoleServiceImpl;


public class StaticFactory {
    public static IRoleService getRoleService(){
        return new RoleServiceImpl();
    }
}
