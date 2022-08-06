package com.atguigu.spring5.testdemo;

import com.atguigu.spring5.Book;
import com.atguigu.spring5.Orders;
import com.atguigu.spring5.User;
import com.atguigu.spring5.bean.Emp;
import com.atguigu.spring5.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {

    @Test
    public void testBean1() {
        //1 加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean2.xml");

        //2 获取配置创建的对象
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }

    @Test
    public void testBean2() {
        //1 加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean4.xml");

        //2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);

        emp.add();
    }
}
