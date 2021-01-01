package cn.itcast.test;


import cn.itcast.service.IRoleService;
import cn.itcast.service.impl.RoleServiceImpl;
import cn.itcast.utils.StringToDateConverter;
import itcast.domain.RoleInfo;
import org.junit.Test;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

public class TestIOC {

    private IRoleService iRoleService;

    @Test
    public void testIOC() throws Exception {
//        ApplicationContext ca = new ClassPathXmlApplicationContext("applicationContext.xml");
//        iRoleService = (IRoleService)ca.getBean("roleService");
//        List<RoleInfo> roleInfos = iRoleService.findAll();
//        for (RoleInfo roleInfo:
//                roleInfos) {
//            System.out.println(roleInfo);
//        }


        ApplicationContext ca = new ClassPathXmlApplicationContext("applicationContext.xml");
        iRoleService = (IRoleService)ca.getBean("roleService");
        List<RoleInfo> roleInfos = iRoleService.findAll();
        for (RoleInfo roleInfo:
                roleInfos) {
            System.out.println(roleInfo);
        }
    }


    /**
     * 模拟Spring Ioc工作流程
     * @throws Exception
     */
    @Test
    public void testBeanFactory() throws Exception {
        // 默认容器实现
        DefaultListableBeanFactory beanRegistry= new DefaultListableBeanFactory();

        // 根据业务对象构造相应的BeanDefinition
        AbstractBeanDefinition definition= new RootBeanDefinition(StringToDateConverter.class);
        // 把bean定义注册到容器中
        beanRegistry.registerBeanDefinition("StringToDateConverter", definition);

        //然后可以从容器中取得这个bean的实例
        BeanFactory container = (BeanFactory)beanRegistry;
        StringToDateConverter converter = (StringToDateConverter) container.getBean("StringToDateConverter");

        String s = "2020-12-27 12:13:14";
        Date date = converter.convert(s);
        System.out.println(date);
    }

    /**
     * 模拟Spring Ioc读取Xml工作流程
     * @throws Exception
     */
    @Test
    public void testBeanfactoryXml() throws Exception{
        // 通常为 BeanDefinitionRegistry的实现类， 以DefaultListableBeanFactory为例
        BeanDefinitionRegistry registry= new DefaultListableBeanFactory();
        //XmlBeanDefinitionReader实现了BeanDefinitionReader接口，用于解析XML文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        // 加载配置文件
        reader.loadBeanDefinitions("classpath:testspringioc.xml");
        // 从容器中获取bean实例
        BeanFactory container = (BeanFactory)registry;
        StringToDateConverter converter = (StringToDateConverter)container.getBean("stringToDateConverter");

        String s = "2020-12-28 12:13:14";
        Date date = converter.convert(s);
        System.out.println(date);
    }

    @Test
    public void testEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "yuliang123";
        String pwd = encoder.encode(password);
        System.out.println(pwd);
    }

}
