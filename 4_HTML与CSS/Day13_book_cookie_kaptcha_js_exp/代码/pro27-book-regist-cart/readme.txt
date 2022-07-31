昨日内容：
1. 显示主页面(index页面)
  - 新建BookDAO 、 BookDAOImpl ： getBookList()
  - 新建BookService 、 BookServiceImpl : getBookList()
  - 新建BookController : index()
  - 编辑index.html
2. 首页上登录成功之后显示欢迎语和购物车数量
3. 点击具体图书的添加到购物车按钮
4. 购物车详情
5. 结账
   1) 订单表添加一条记录
   2) 订单详情表添加7条记录
   3) 购物车项表中需要删除对应的7条记录
6. 关于订单信息中的订单数量问题
7. 编辑购物车
8. 关于金额的精度问题
9. 过滤器判断是否是合法用户：
   - 解决方法：新建SessionFilter ， 用来判断session中是否保存了currUser
   - 如果没有currUser，表明当前不是一个登录合法的用户，应该跳转到登录页面让其登录

   - 现在添加了过滤器之后，出现了如下错误：
   localhost 将您重定向的次数过多。
   尝试清除 Cookie.
   ERR_TOO_MANY_REDIRECTS

2. 今日内容：
  1. 在注册页面显示验证码
  2. 注册功能实现


