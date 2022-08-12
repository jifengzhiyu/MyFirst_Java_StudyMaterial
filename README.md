# MyFirst_Java_StudyMaterial

笔记：https://heavy_code_industry.gitee.io/code_heavy_industry/

## 1_java基础

![image-20220326222510587](Pic/image-20220326222510587.png)

### day1

- 文件与编译
- 文档注释
- 打印
- 标识符(ldentifier)
- 名称命名规范
- Java强类型语言
- 基本数据类型
- 常量
- String变量
- 进制
- 运算符
- Scanner类
- 流程控制
- 随机数
- 引用数据类型(数组)

### day2

- 面向过程(POP)与面向对象(OOP)
- 对象的内存解析
- 属性（成员变量）VS局部变量
- 类中方法的声明和使用
- 方法的重载(overload)
- 可变个数形参
- 变量的赋值
- 递归
- 面向对象特征之一：封装和隐藏
   - getter&setter
   - 四种访问权限修饰符（可见性）
- 构造器
- 属性赋值的先后顺序
- JavaBean
- UML类图
- 关键字：this的使用
- package:关键字
- import关键字
- MVC介绍
- 客户信息管理项目
- 面向对象特征之二：继承性
- 方法的重写(override)
- super关键字
- 子类对象实例化的全过程
- 面向对象特征之三：多态性
  - 强制类型转换
    - instancetype
- Object类
  - ==和equals()区别
  - toString()
- 单元测试JUnit
- 包装类

### day3

- 关键字：static
  - 单例（设计模式）
- Main方法
- 代码块
- 关键字：final
- 抽象类与抽象方法
  - 抽象类的匿名子类
  - 模版方法设计模式
- 接口
  - 面向接口编程
  - 接口的匿名实现类
  - 代理模式
  - 工厂模式
  - Java8接口新特性
- 内部类
- 异常处理
  - 常见异常
  - try-catch-finally
  - finally
  - throws
  - 手动抛出异常&自定义异常类

### day4

- IDEA&Eclipse
- 进线程
  - 创建thread(继承法)
  - thread相关方法
  - 创建thread(Runnable接口法)
  - 线程的生命周期
  - 同步机制（同步锁)
    - 同步代码块
    - 同步方法
  - 改进懒汉式单例模式
  - 线程的死锁
  - Lock
  - 线程通信
  - 创建thread(Callable接口)
  - 创建thread(线程池法)
    - Runnablef使用
    - Callable使用
- String
  - 内存
  - 与其他结构之间的转换
  - 与基本数据类型、包装类
  - 与char[]
  - 与byte[]
- String、 StringBuffer、StringBuilder
  - 异同&创建
  - StringBuffer和StringBuilder的使用
  - String算法
    - 字符串指定反转
    - 统计出现次数
    - 最大相同子串
  - 日期时间
    - System.currentTimeMillis()
    - java.util.Date
    - SimpleDateFormat
    - Calendar
    - Java8 java.timeAPl
      - LocalDateTime.系列：重要
      - Instant
      - DateTimeFormatter
    - 对象的排序
      - 自然排序Comparable
      - 定制排序Comparator
- System类
- Math类
- BigInteger&BigDecimal

### day5

- 枚举类
  - 自定义枚举类
  - enum
- Annotation注解
  - 自定义Annotation
  - 元注解
  - 利用反射获取注解信息
  - 可重复注解
  - 类型注解
- 集合框架
  - Collection接口
  - Iterator迭代器接口
    - 遍历输出集合
    - remove()
    - foreach增强for循环
  - Collection子接口一：List
    - ArrayList(代替数组)
    - LinkedList
    - Vector((不太用)
  - Collection子接口二：Set
    - HashSet
    - LinkedHashSet
    - TreeSet
  - Map接口
    - HashMap
      - LinkedHashMap
    - TreeMap
    - Hashtable
      - Properties
    - Collections工具类
- 数据结构

### day6

- 泛型
  - 在集合中使用泛型
  - 自定义泛型结构
    - 自定义泛型类，泛型方法
  - 泛型与异常
  - 泛型在继承上的表现
  - 通配符的使用
    - 一般使用
    - 有限制的通配符
  - 泛型的应用
    - 比较
    - 泛型嵌套
- lO流
  - File类
    - 构造器
    - 使用
    - 创建删除
  - IO流原理及流的分类
  - 节点流（文件流）
    - FileReader
    - FileWriter
    - 二合一
    - FilelnputStream
    - FilelnputOutputStream
  - 缓冲流
    - BufferedInputOutputStream
    - BufferedReaderWriter
  - 转换流（编码）
    - InputStreamReader
    - InputStreamReaderWriter
  - 标准输入输出流
  - 打印流
  - 数据流
  - 对象流
  - 随机存取文件流
  - ByteArrayOutputStream
  - NIO.2中Path、Paths、Files类的使用
- 导入三方jar包

### day7

- 网络编程
  - 网络通信要素概述
  - 通信要素1：IP和端口号
  - 通信要素2：网络协议
    - TCP网络编程
    - UDP网络编程
  - URL编程
- 反射机制
  - 理解Class类并获取Class实例
  - 类的加载与ClassLoader的理解
    - 读取配置文件
  - 创建运行时类的对象
  - 获取运行时类的完整结构
    - 属性结构
    - 权限修饰符 数据类型 变量名
    - 方法结构
    - 构造器结构
    - 运行时类的父类
    - 运行时类的带泛型的父类
    - 运行时类的带泛型的父类的泛型
    - 运行时类实现的接口
    - 运行时类所在的包
    - 运行时类声明的注解
  - 调用运行时类的指定结构
    - 属性
    - 方法
    - 构造器
  - 反射的应用：动态代理
    - 静态代理举例
    - 动态代理的举例
- Java8新特性
  - Lambda表达式
  - 函数式(Functional)接口
    - java内置的4大核心函数式接口
  - 方法引用与构造器引用
    - 方法引用
    - 构造器引用
    - 数组引用
  - 强大的Stream API
    - Stream实例化
    - Stream的中间操作
      - 筛选与切片
      - 映射
      - 排序
    - Stream的终止操作
      - 匹配与杳找
      - 归约
      - 收集
- Optional类

## 2_MySQL

### day1

- 数据库概述
  - 关系型数据库&非关系型数据库
  - 关系型数据库设计规则
    - 表、记录、字段
    - 表的关联关系
- 基本的SELECT语句
  - SQL分类
  - SQL语言的规则与规范
  - 注释
  - 命名规则
  - 基本的SELECT语句
    - SELECT...FROM
    - 列的别名
    - 去除重复行
    - 空值参与运算
    - 着重号
    - 查询常数
    - 显示表结构
    - 过滤数据
- 运算符
  - 算术运算符
  - 比较运算符
  - 逻辑运算符
  - 位运算符
- 排序与分页
  - 排序
    - 升序&&降序
    - 别名问题
    - 二级排序
  - 分页
- 多表查询
  - 笛卡尔积（或交叉连接）的理解
  - 多表查询的分类
    - 等值连接Vs非等值连接
    - 自连接Vs非自连接
    - 内连接Vs外连接
      - 内连接(SQL92)
      - 外连接(SQL92)
  - SQL99语法实现多表查询
    - JOIN ...ON(推荐使用)
      - 内连接
      - 外连接
  - UNION的使用
  - 7种JOIN的实现
  - SQL99语法新特性
    - 自然连接
    - USING连接
- 注意

### day2

- 单行函数
  - 数值函数
  - 字符串函数
  - 日期和时间函数
  - 流程控制函数
  - 加密与解密函数
  - MySQL信息函数
  - 其他函数
- 聚合函数
  - AVG和SUM函数
  - MIN和MAX函数
  - COUNT函数
  - GROUP BY
    - WITH ROLLUP
  - HAVING
  - SQL底层执行原理
    - SELECT语句的完整结构
    - SQL语句的执行过程
- 子查询
  - 子查询的基本使用与分类
  - 单行子查询
    - 单行操作符
    - HAVING中的子查询
    - CASE中的子查询
    - 子查询中的空值问题
    - 非法使用子查询
  - 多行子查询
    - 多行比较操作符
    - 空值问题
  - 相关子查询
    - EXISTS与NOT EXISTS关键字
- 创建和管理表
  - 基础知识
    - 一条数据存储的过程
  - 标识符命名规则
    - 阿里巴巴字段命名
  - 创建和管理数据库
    - 创建数据库
    - 使用数据库
    - 修改数据库
    - 删除数据库
  - 创建表
  - 修改表
    - 追加一个列
    - 修改一个列
    - 重命名一个列
    - 删除一个列
  - 重命名表
  - 删除表
  - 清空表
  - COMMIT和ROLLBACK
  - MySQL8新特性一DDL的原子化
- 数据处理之增删改
  - 插入数据
  - 更新数据
  - 删除数据
  - MySQL8新特性：计算列

### day3

- MySQL数据类型
  - character set
  - 整数类型
  - 可选属性
    - M&ZEROFILL
    - UNSIGNED
  - 选择
  - 浮点类型
  - 数据精度
  - 定点数类型
    - 浮点数Vs定点数
  - 位类型：BT
    - 进制转换
  - 日期与时间类型
    - YEAR类型
    - DATE类型
    - TIME类型
    - DATETIME类型
    - TIMESTAMP类型
    - TIMESTAMP和DATETIME的区别
    - 选择
  - 文本字符串类型
    - CHAR与VARCHAR类型
      - CHAR类型
      - VARCHAR类型
      - 选择
    - TEXT类型
    - ENUM类型
    - SET类型
  - 二进制字符串类型
    - BINARY与VARBINARY类型
    - BLOB类型
  - JSON类型
  - 小结及选择建议
- 约束1
  - 查看某个表已有的约束
  - 非空约束
  - 唯一性约束
    - 复合唯一性约束
    - 删除唯一性约束
  - 主键约束
    - 复合主键约束
  - 自增列
    - 删除自增约束
    - MySQL8.0新特性一自增变量的持久化

### day4

- 约束2
  - FOREIGN KEY约束
    - 约束等级
    - 删除外键约束
    - 开发规范
  - CHECK约束
  - DEFAULT约束
    - 删除默认值约束
  - 我的总结
    - 总结删除约束
    - 约束的命名
- 视图
  - 创建视图
    - 创建单表视图
    - 创建多表联合视图
    - 利用视图对数据进行格式化
    - 基于视图创建视图
  - 查看视图
  - 更新视图的数据
    - 一般情况
    - 不可更新的视图
  - 修改视图
  - 删除视图
  - 视图优缺点
    - 优点
    - 缺点
- 存储过程与函数
  - 存储过程概述
    - 理解
    - 分类
  - 创建存储过程
  - 调用存储过程
  - 存储函数的使用
  - 调用存储函数
  - 报错处理
  - 存储过程和函数的查看、修改、 删除查看
    - 修改
    - 删除
  - 存储过程使用的争议
  - 优点
  - 缺点

### day5

- 变量、流程控制与游标
  - 变量
    - 系统变量
      - 查看修改系统变量
    - 用户变量
      - 初始化问题
      - 会话用户变量
      - 局部变量
  - 定义条件与处理程序
    - 定义条件
    - 定义处理程序
  - 流程控制
    - 分支结构之IF
    - 分支结构之CASE
    - 循环结构之LOOP
    - WHILE
    - 循环结构之REPEAT
    - 对比三种循环结构
    - 跳转语句之LEAVE语句
    - 跳转语句之TERATE语句
  - 游标
    - 什么是游标（或光标）
    - 使用游标步骤
  - MySQL8.0的新特性一全局变量的持久化
- 触发器
  - 触发器的创建
  - 查看、删除触发器
  - 触发器的优缺点
    - 优点
    - 缺点
    - 注意点

## 3_JDBC

- 在一个.md文件

## 4_JavaWeb

- 看文件夹名称

## 5_Spring5

### 1_Spring 框架概述

（1）轻量级开源 JavaEE 框架，为了解决企业复杂性，两个核心组成：IOC 和 AOP

（2）Spring5.2.6 版本

### 2_IOC 容器

（1）IOC 底层原理（工厂、反射等）

（2）IOC 接口（BeanFactory） 

（3）IOC 操作 Bean 管理（基于 xml） 

（4）IOC 操作 Bean 管理（基于注解）

### 3_Aop

（1）AOP 底层原理：动态代理，有接口（JDK 动态代理），没有接口（CGLIB 动态代理）

（2）术语：切入点、增强（通知）、切面

（3）基于 AspectJ 实现 AOP 操作

### 4_JdbcTemplate

（1）使用 JdbcTemplate 实现数据库 curd 操作

（2）使用 JdbcTemplate 实现数据库批量操作

### 5_事务管理

（1）事务概念

（2）重要概念（传播行为和隔离级别）

（3）基于注解实现声明式事务管理

（4）完全注解方式实现声明式事务管理

### 6_Spring5 新功能

（1）整合日志框架

（2）@Nullable 注解

（3）函数式注册对象

（4）整合 JUnit5 单元测试框架

（5）SpringWebflux 使用

## 6_SpringMVC

- 在一个.md文件
