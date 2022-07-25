package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//演示Session
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session,如果获取不到，则创建一个新的
        HttpSession session = request.getSession() ;
        System.out.println("session ID : " + session.getId());

    }
}
