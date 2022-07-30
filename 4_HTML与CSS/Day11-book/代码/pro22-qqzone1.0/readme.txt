1.熟悉QQZone业务需求
  1) 用户登录
  2) 登录成功，显示主界面。左侧显示好友列表；上端显示欢迎词。如果不是自己的空间，显示超链接：返回自己的空间；下端显示日志列表
  3) 查看日志详情：
        - 日志本身的信息（作者头像、昵称、日志标题、日志内容、日志的日期）
        - 回复列表（回复者的头像、昵称、回复内容、回复日期）
        - 主人回复信息
  4) 删除日志
  5) 删除特定回复
  6) 删除特定主人回复
  7) 添加日志、添加回复、添加主人回复
  8) 点击左侧好友链接，进入好友的空间
2.数据库设计
  1） 抽取实体 : 用户登录信息、用户详情信息 、 日志 、 回贴  、 主人回复
  2） 分析其中的属性：
    - 用户登录信息：账号、密码、头像、昵称
    - 用户详情信息：真实姓名、星座、血型、邮箱、手机号.....
    - 日志：标题、内容、日期、作者
    - 回复：内容、日期、作者、日志
    - 主人回复：内容、日期、作者、回复
  3） 分析实体之间的关系
    - 用户登录信息 ： 用户详情信息      1：1 PK
    - 用户 ： 日志                   1：N
    - 日志 ： 回复                   1：N
    - 回复 ： 主人回复                1：1 UK
    - 用户 ： 好友                   M ： N
3.数据库的范式：
  1） 第一范式：列不可再分
  2） 第二范式：一张表只表达一层含义（只描述一件事情）
  3） 第三范式：表中的每一列和主键都是直接依赖关系，而不是间接依赖
4.数据库设计的范式和数据库的查询性能很多时候是相悖的，我们需要根据实际的业务情况做一个选择：
  - 查询频次不高的情况下，我们更倾向于提高数据库的设计范式，从而提高存储效率
  - 查询频次较高的情形，我们更倾向于牺牲数据库的规范度，降低数据库设计的范式，允许特定的冗余，从而提高查询的性能

5.QQZone登录功能实现出现的四个错误：
 1) URL没修改，用的还是fruitdb
 2）
 3）rsmd.getColumnName() 和 rsmd.getColumnLabel()
 4）Can not set com.atguigu.qqzone.pojo.UserBasic field com.atguigu.qqzone.pojo.Topic.author to java.lang.Integer
 5) left.html页面没有样式，同时数据也不展示，原因是：我们是直接去请求的静态页面资源，那么并没有执行super.processTemplate()，也就是thymeleaf没有起作用
    (之前的表单也是这个原因)
    解决方法：
    - 新增PageController，添加page方法:
    public String page(String page){
        return page ;       // frames/left
    }
    目的是执行super.processTemplate()方法，让thymeleaf生效

今日内容：
1. top.html页面显示登录者昵称、判断是否是自己的空间
   1)显示登录者昵称： ${session.userBasic.nickName}
   2)判断是否是自己的空间 : ${session.userBasic.id!=session.friend.id}
     如果不是期望的效果，首先考虑将两者的id都显示出来

2. 点击左侧的好友链接，进入好友空间
   1) 根据id获取指定userBasic信息，查询这个userBasic的topicList，然后覆盖friend对应的value
   2) main页面应该展示friend中的topicList，而不是userBasic中的topicList
   3) 跳转后，在左侧（left）中显示整个index页面
      - 问题：在left页面显示整个index布局
      - 解决：给超链接添加target属性：   target="_top" 保证在顶层窗口显示整个index页面

   4) top.html页面需要修改： "欢迎进入${session.friend}"
      top.html页面的返回自己空间的超链接需要修改：
      <a th:href="@{|/user.do?operate=friend&id=${session.userBasic.id}|}" target="_top">

3. 日志详情页面实现
   1) 已知topic的id，需要根据topic的id获取特定topic
   2) 获取这个topic关联的所有的回复
   3) 如果某个回复有主人回复，需要查询出来
   - 在TopicController中获取指定的topic
   - 具体这个topic中关联多少个Reply，由ReplyService内部实现
   4) 获取到的topic中的author只有id，那么需要在topicService的getTopic方法中封装，在查询topic本身信息时，同时调用userBasicService中的获取userBasic方法，给author属性赋值
   5) 同理，在reply类中也有author，而且这个author也是只有id，那么我们也需要根据id查询得到author，最后设置关联

4. 添加回复

5. 删除回复
   1) 如果回复有关联的主人回复，需要先删除主人回复
   2) 删除回复
   Cannot delete or update a parent row: a foreign key constraint fails
   (`qqzonedb`.`t_host_reply`, CONSTRAINT `FK_host_reply` FOREIGN KEY (`reply`) REFERENCES `t_reply` (`id`))
    我们在删除回复表记录时，发现删除失败，原因是：在主人回复表中仍然有记录引用待删除的回复这条记录
    如果需要删除主表数据，需要首先删除子表数据

6. 删除日志
   1) 删除日志，首先需要考虑是否有关联的回复
   2) 删除回复，首先需要考虑是否有关联的主人回复
   3) 另外，如果不是自己的空间，则不能删除日志