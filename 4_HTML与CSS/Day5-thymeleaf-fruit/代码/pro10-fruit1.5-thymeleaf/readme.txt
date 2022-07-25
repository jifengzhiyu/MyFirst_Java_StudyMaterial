review:
1. post提交方式下的设置编码，防止中文乱码
   request.setCharacterEncoding("utf-8");
   get提交方式，tomcat8开始，编码不需要设置
   tomcat8之前，get方式设置比较麻烦：
   String fname = request.getParameter("fname");
   byte[] bytes = fname.getBytes("iso-8859-1");
   fname = new String(bytes,"UTF-8");

2. Servlet继承关系以及生命周期
   1） Servlet接口 ： init() , service() , destroy()
       GenericServlet抽象子类： abstract service();
       HttpServlet抽象子类：实现了service方法，在service方法内部通过request.getMethod()来判断请求的方式，
            然后根据请求的方式去调用内部的do方法。每一个do方法进行了简单实现，主要是如果请求方式不符合，则报405错误。
            目的是让我们的Servlet子类去重写对应的方法（如果重写的不对，则使用父类的405错误实现）
   2） 生命周期：实例化、初始化、服务、销毁
        - Tomcat负责维护Servlet实例的生命周期
        - 每个Servlet在Tomcat容器中只有一个实例，它是线程不安全的
        - Servlet的启动时机：<load-on-startup>
        - Servlet3.0开始支持注解: @WebServlet

3. HTTP协议：
   1） 由 Request 和 Response 两部分组成
   2） 请求包含了三部分：请求行、请求消息头、请求主体： 普通的get方式请求-query string；post方式- form data ； json格式 - request payload
   3） 响应包含了三部分：响应行、响应消息头、响应主体

4. HttpSession
   1） HttpSession ：表示 会话
   2） 为什么需要HttpSession ， 原因是因为 Http协议是无状态的
   3） Session保存作用域 ：一次会话范围都有效 ； void session.setAttribute(k,v) ,Object session.getAttribute(k)
   4） 其他的API： session.getId() , session.isNew() , session.getCreationTime() , session.invalidate() 等等

5. 服务器端转发和客户端重定向
   1) 服务器端转发 ： request.getRequestDispatcher("index.html").forward(request,response);
   2) 客户端重定向： response.sendRedirect("index.html");

6. thymeleaf的部分标签
   1） 使用步骤： 添加jar ， 新建ViewBaseServlet(有两个方法） ， 配置两个<context-param> : view-prefix , view-suffix
   2） 部分标签： <th:if> , <th:unless> , <th:each> , <th:text>

今日内容：
1. 保存作用域
   原始情况下，保存作用域我们可以认为有四个： page（页面级别，现在几乎不用） , request（一次请求响应范围） , session（一次会话范围） , application（整个应用程序范围）
   1） request：一次请求响应范围
   2） session：一次会话范围有效
   3） application： 一次应用程序范围有效

2. 路径问题
   1） 相对路径
   2） 绝对路径

3. 实现库存系统的功能
