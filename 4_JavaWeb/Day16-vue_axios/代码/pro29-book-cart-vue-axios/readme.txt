昨日内容：：
  1. 在注册页面显示验证码
   1) 添加jar
   2) 在web.xml文件中配置KaptchaServlet，以及配置相关的属性
   3) 在页面上访问这个Servlet，然后这个Servlet做两件事情：
      - 在页面上显示验证码图片
      - 在session作用域中保存验证码信息，对应的key存储在Constans这个常量接口中
   4) 用户在注册页面中输入验证码发送给服务器，那么需要和session中保存的进行比较
  2. 注册功能实现
  3. js中的正则表达式
   1) 三步骤：定义正则表达式对象（两种方式）；定义待校验的字符串；校验
   2) 正则表达式的规则：
      g  i  m
      . , \w , \W , \d , \D , \b , \s , \S , ^ , $
      [] , [^] , [-]
      * , + , ? , {n} , {n,} , {n,m}
      |

今日内容：
  1. 注册页面表单验证
    1) <form>有一个事件 onsubmit ,
        onsubmit="return false" , 那么表单点击提交按钮时不会提交
        onsubmit="return true" ,  那么表单点击提交按钮时会提交

    2) 获取文档中某一个节点的方式：
        //DOM:Document
        //var unameTxt = document.getElementById("unameTxt");
        //BOM:Browser
        //document.forms[0].uname


  2. 原生的Ajax（了解）
    第一步： 客户端发送异步请求；并绑定对结果处理的回调函数
    1) <input type="text" name="uname" onblur="ckUname()"/>
    2) 定义ckUname方法：
       - 创建XMLHttpRequest对象
       - XMLHttpRequest对象操作步骤：
         - open(url,"GET",true)
         - onreadyStateChange 设置回调
         - send() 发送请求
       - 在回调函数中需要判断XMLHttpRequest对象的状态:
         readyState(0-4) , status(200)
    第二步：服务器端做校验，然后将校验结果响应给客户端