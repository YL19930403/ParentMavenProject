package cn.itcast.factory;


import cn.itcast.service.IRoleService;
import cn.itcast.service.impl.RoleServiceImpl;

public class InstanceFactory {
    // 模拟工厂类
    public IRoleService getRoleService(){
        return new RoleServiceImpl();
    }
}
