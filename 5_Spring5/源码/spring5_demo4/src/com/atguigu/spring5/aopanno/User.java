package com.atguigu.spring5.aopanno;

import org.springframework.stereotype.Component;

//被增强的类
@Component
public class User {
    public void add() {
        int i = 10/0;
        System.out.println("add.......");
    }
}
