# 约束_2

## FOREIGN KEY约束

- 限定某个表的某个字段的引用完整性。比如：员工表的员工所在部门的选择，必须在部门表能找到对应的部分。

- 主表（父表）：被引用的表，被参考的表

  从表（子表）：引用别人的表，参考别人的表

  例如：员工表的员工所在部门这个字段的值要参考部门表：部门表是主表，员工表是从表。

- （1）从表的外键列，必须引用/参考主表的主键或唯一约束的列

  为什么？因为被依赖/被参考的值必须是唯一的

  （2）在创建外键约束时，如果不给外键约束命名，**默认名不是列名，而是自动产生一个外键名**（例如student_ibfk_1;），也可以指定外键约束名。

  （3）创建(CREATE)表时就指定外键约束的话，先创建主表，再创建从表

  （4）删表时，先删从表（或先删除外键约束），再删除主表

  （5）当主表的记录被从表参照时，主表的记录将不允许删除，如果要删除数据，需要先删除从表中依赖该记录的数据，然后才可以删除主表的数据

  （6）在“从表”中指定外键约束，并且一个表可以建立多个外键约束

  （7）从表的外键列与主表被参照的列名字可以不相同，但是数据类型必须一样，逻辑意义一致。如果类型不一样，创建子表时，就会出现错误“ERROR 1005 (HY000): Can't create table'database.tablename'(errno: 150)”。

  例如：都是表示部门编号，都是int类型。

  （8）**当创建外键约束时，系统默认会在所在的列上建立对应的普通索引**。但是索引名是外键的约束名。（根据外键查询效率很高）

  （9）删除外键约束后，必须 手动 删除对应的索引

```sql
-- 建表时
create table 主表名称(
 字段1 数据类型 primary key,
 字段2 数据类型 
);
create table 从表名称(
字段1 数据类型 primary key,
字段2 数据类型,
[CONSTRAINT <外键约束名称>] FOREIGN KEY（从表的某个字段) references 主表名(被参考字段)
);
#(从表的某个字段)的数据类型必须与主表名(被参考字段)的数据类型一致，逻辑意义也一样
#(从表的某个字段)的字段名可以与主表名(被参考字段)的字段名一样，也可以不一样
-- FOREIGN KEY: 在表级指定子表中的列
-- REFERENCES: 标示在父表中的列

-- 建表后
ALTER TABLE 从表名 
ADD [CONSTRAINT 约束名] FOREIGN KEY (从表的字段) REFERENCES 主表名(被引用 字段) [on update xx][on delete xx];
```

```sql
#7.foreign key (外键约束)
#7.1 在CREATE TABLE 时添加
#主表和从表；父表和子表
#①先创建主表
CREATE TABLE dept1(
dept_id INT,
dept_name VARCHAR(15)
);

#主表中的dept_id上一定要有主键约束或唯一性约束。
#③ 添加
ALTER TABLE dept1
ADD PRIMARY KEY (dept_id);

DESC dept1;

#④ 再创建从表
CREATE TABLE emp1(
emp_id INT PRIMARY KEY AUTO_INCREMENT,
emp_name VARCHAR(15),
department_id INT,

#表级约束
CONSTRAINT fk_emp1_dept_id FOREIGN KEY (department_id) REFERENCES dept1(dept_id)
);

DESC emp1;


SELECT * FROM information_schema.table_constraints 
WHERE table_name = 'emp1';
```

```sql
#7.3 在ALTER TABLE时添加外键约束
CREATE TABLE dept2(
dept_id INT PRIMARY KEY,
dept_name VARCHAR(15)
);

CREATE TABLE emp2(
emp_id INT PRIMARY KEY AUTO_INCREMENT,
emp_name VARCHAR(15),
department_id INT
);

ALTER TABLE emp2
ADD CONSTRAINT fk_emp2_dept_id FOREIGN KEY(department_id) REFERENCES dept2(dept_id);

SELECT * FROM information_schema.table_constraints 
WHERE table_name = 'emp2';
```

- 约束关系是针对双方的

  添加了外键约束后，主表的修改和删除数据受约束

  添加了外键约束后，从表的添加和修改数据受约束

  在从表上建立外键，要求主表必须存在

  删除主表时，要求从表从表先删除，或将从表中外键引用该主表的关系先删除

### 约束等级

- Cascade方式 ：在父表上update/delete记录时，同步update/delete掉子表的匹配记录

  Set null方式 ：在父表上update/delete记录时，将子表上匹配记录的列设为null，但是要注意子表的外键列不能为not null 

  No action方式 ：如果子表中有匹配的记录，则不允许对父表对应候选键进行update/delete操作

  Restrict方式 ：同no action， 都是立即检查外键约束

  Set default方式 （在可视化工具SQLyog中可能显示空白）：父表有变更时，子表将外键列设置成一个默认的值，但Innodb不能识

- 如果没有指定等级，就相当于Restrict方式。

- 对于外键约束，最好是采用: ON UPDATE CASCADE ON DELETE RESTRICT 的方式。

```sql
#演示：
# on update cascade on delete set null
CREATE TABLE dept(
    did INT PRIMARY KEY,		#部门编号
    dname VARCHAR(50)			#部门名称
);

CREATE TABLE emp(
    eid INT PRIMARY KEY,  #员工编号
    ename VARCHAR(5),     #员工姓名
    deptid INT,		  #员工所在的部门
    FOREIGN KEY (deptid) REFERENCES dept(did)  ON UPDATE CASCADE ON DELETE SET NULL
    #把修改操作设置为级联修改等级，把删除操作设置为set null等级
);

INSERT INTO dept VALUES(1001,'教学部');
INSERT INTO dept VALUES(1002, '财务部');
INSERT INTO dept VALUES(1003, '咨询部');

INSERT INTO emp VALUES(1,'张三',1001); #在添加这条记录时，要求部门表有1001部门
INSERT INTO emp VALUES(2,'李四',1001);
INSERT INTO emp VALUES(3,'王五',1002);

UPDATE dept
SET did = 1004
WHERE did = 1002;

DELETE FROM dept
WHERE did = 1004;

DELETE FROM dept WHERE did = 1001;

SELECT * FROM dept;
SELECT * FROM emp;
#结论：对于外键约束，最好是采用: `ON UPDATE CASCADE ON DELETE RESTRICT` 的方式。
```

### 删除外键约束

```sql
(1)第一步先查看约束名和删除外键约束 
SELECT * FROM information_schema.table_constraints 
WHERE table_name = '表名称';
#查看某个表的约束名 

ALTER TABLE 从表名 
DROP FOREIGN KEY 外键约束名;

（2）第二步查看索引名和删除索引。（注意，只能手动删除）
SHOW INDEX FROM 表名称;
#查看某个表的索引名 
ALTER TABLE 从表名 
DROP INDEX 索引名;
```

```sql
#7.5 删除外键约束
#一个表中可以声明有多个外键约束
USE dbtest13;

SELECT * FROM information_schema.table_constraints 
WHERE table_name = 'emp1';

#删除外键约束
ALTER TABLE emp1
DROP FOREIGN KEY fk_emp1_dept_id;

#再手动的删除外键约束对应的普通索引
SHOW INDEX FROM emp1;

ALTER TABLE emp1
DROP INDEX fk_emp1_dept_id;
```

### 开发规范

>在 MySQL 里，外键约束是有成本的，需要消耗系统资源。对于大并发的 SQL 操作，有可能会不适合。比如大型网站的中央数据库，可能会 因为外键约束的系统开销而变得非常慢 。所以， MySQL 允许你不使用系统自带的外键约束，在 应用层面 完成检查数据一致性的逻辑。也就是说，即使你不用外键约束，也要想办法通过应用层面的附加逻辑，来实现外键约束的功能，确保数据的一致性。

**阿里开发规范** 

- 【 强制 】不得使用外键与级联，一切外键概念必须在应用层解决。
- 说明：（概念解释）学生表中的 student_id 是主键，那么成绩表中的 student_id 则为外键。如果更新学生表中的 student_id，同时触发成绩表中的 student_id 更新，即为级联更新。外键与级联更新适用于 单 机低并发 ，不适合 分布式 、 高并发集群 ；级联更新是强阻塞，存在数据库 更新风暴 的风险；外键影响数据库的 插入速度 。 

## CHECK约束

- 检查某个字段的值是否符号xx要求，一般指的是值的范围
- MySQL5.7 可以使用check约束，但check约束对数据验证没有任何作用。添加数据时，没有任何错误或警告。但是MySQL 8.0**中可以使用**check**约束了**。

```sql
#8. check 约束
# MySQL5.7 不支持CHECK约束，MySQL8.0支持CHECK约束。
CREATE TABLE test10(
id INT,
last_name VARCHAR(15),
salary DECIMAL(10,2) CHECK(salary > 2000)
-- gender char check ('男' or '女')
);

INSERT INTO test10
VALUES(1,'Tom',2500);

#添加失败
INSERT INTO test10
VALUES(2,'Tom1',1500);

SELECT * FROM test10;

-- 建表后添加约束
ALTER TABLE test110
MODIFY salary DECIMAL(10,2) CHECK(salary > 2000);
```

## DEFAULT约束

- 给某个字段/某列指定默认值，一旦设置默认值，在插入数据时，如果此字段没有显式赋值，则赋值为默认值。

```sql
-- 建表时
create table 表名称(
字段名 数据类型 primary key,
字段名 数据类型 unique key not null, 
字段名 数据类型 unique key, 
字段名 数据类型 not null default 默认值
);
-- 说明：默认值约束一般不在唯一键和主键列上加

-- 建表后
alter table 表名称
modify 字段名 数据类型 default 默认值;
#如果这个字段原来有非空约束，你还保留非空约束，那么在加默认值约束时，还得保留非空约束，否则非空约束就被删除了 
#同理，在给某个字段加非空约束也一样，如果这个字段原来有默认值约束，你想保留，也要在modify语句中保留默认值约束，否则就删除了
alter table 表名称
modify 字段名 数据类型 default 默认值 not null;
```

```sql
#9.DEFAULT约束
#9.1 在CREATE TABLE添加约束
CREATE TABLE test11(
id INT,
last_name VARCHAR(15),
salary DECIMAL(10,2) DEFAULT 2000
);

DESC test11;

INSERT INTO test11(id,last_name,salary)
VALUES(1,'Tom',3000);

INSERT INTO test11(id,last_name)
VALUES(2,'Tom1');

SELECT * 
FROM test11;
```

```sql
#9.2 在ALTER TABLE添加约束
CREATE TABLE test12(
id INT,
last_name VARCHAR(15),
salary DECIMAL(10,2)
);

DESC test12;

ALTER TABLE test12
MODIFY salary DECIMAL(8,2) DEFAULT 2500;
```

### 删除默认值约束

```sql
alter table 表名称
modify 字段名 数据类型 ;
#删除默认值约束，如果有非空约束，也一并删除

alter table 表名称
modify 字段名 数据类型 not null;
#删除默认值约束，保留非空约束
```

![image-20220622231313807](Pic/image-20220622231313807.png)

## 我的总结

### 总结删除约束

- 非空约束 自增约束 默认值约束类似 

```sql
alter table 表名称 
modify 字段名 数据类型;
```

- 删除唯一性约束：删除索引；删除外键约束：删除约束+手动删述索引

### 约束的命名

- 不支持命名的约束（只能声明在列级约束）：默认约束、非空约束、自增列、check约束；主键约束不用命名

  外键约束命名：`fk_表名_主表参考字段` 

  ![image-20220623084124859](Pic/image-20220623084124859.png)

  唯一性约束命名：`uk_表名_字段名`

- 建表后添加约束:

```sql
alter table 表名称
modify 字段名 数据类型 添加的约束;

-- 添加主键特殊
alter table 表名称
add primary key(字段列表);

-- 添加外键特殊
```

- 一个字段添加多个约束:

```sql
-- 注意顺序
ALTER TABLE books
MODIFY id INT PRIMARY KEY AUTO_INCREMENT;
```

# 视图

![image-20220623091311926](Pic/image-20220623091311926.png)

- ① 视图，可以看做是一个虚拟表，本身是不存储数据的。
    视图的本质，就可以看做是存储起来的SELECT语句

  ② 视图中SELECT语句中涉及到的表，称为基表

  ③ 针对视图做DML操作，会影响到对应的基表中的数据。反之亦然。

  ④ 视图本身的删除，不会导致基表中数据的删除。

  ⑤ 视图的应用场景：针对于小型项目，不推荐使用视图。针对于大型项目，可以考虑使用视图。

  ⑥ 视图的优点：简化查询; 控制数据的访问

## 创建视图

- **在** CREATE VIEW **语句中嵌入子查询**

```sql
CREATE [OR REPLACE]
[ALGORITHM = {UNDEFINED | MERGE | TEMPTABLE}] 
VIEW 视图名称 [(字段列表)] 
AS 查询语句
[WITH [CASCADED|LOCAL] CHECK OPTION];

-- 精简版
CREATE VIEW 视图名称 
AS 查询语句
```

### 创建单表视图

- 在创建视图时，没有在视图名后面指定字段列表，则视图中字段列表默认和SELECT语句中的字段列表一致。如果SELECT语句中给字段取了别名，那么视图中的字段名和别名相同。

```sql
#准备工作
CREATE DATABASE dbtest14;

USE dbtest14;

CREATE TABLE emps
AS
SELECT *
FROM atguigudb.`employees`;
-- 将已有表的数据存储在新的表，会损失约束

CREATE TABLE depts
AS
SELECT *
FROM atguigudb.`departments`;

SELECT * FROM emps;

SELECT * FROM depts;

DESC emps;

DESC atguigudb.employees;
```

```sql
#2.1 针对于单表
#情况1：视图中的字段与基表的字段有对应关系
CREATE VIEW vu_emp1
AS
SELECT employee_id,last_name,salary
FROM emps;

SELECT * FROM vu_emp1;

#确定视图中字段名的方式1：
CREATE VIEW vu_emp2
AS
SELECT employee_id emp_id,last_name lname,salary 
#查询语句中字段的别名会作为视图中字段的名称出现
FROM emps
WHERE salary > 8000;

#确定视图中字段名的方式2：
CREATE VIEW vu_emp3(emp_id,NAME,monthly_sal) 
#小括号内字段个数与SELECT中字段个数相同
AS
SELECT employee_id,last_name,salary 
FROM emps
WHERE salary > 8000;

SELECT * FROM vu_emp3;

#情况2：视图中的字段在基表中可能没有对应的字段
CREATE VIEW vu_emp_sal
AS
SELECT department_id,AVG(salary) avg_sal
FROM emps
WHERE department_id IS NOT NULL
GROUP BY department_id;

SELECT * FROM vu_emp_sal;
```

### 创建多表联合视图

```sql
#2.2 针对于多表
CREATE VIEW vu_emp_dept
AS
SELECT e.employee_id,e.department_id,d.department_name
FROM emps e JOIN depts d
ON e.`department_id` = d.`department_id`;

SELECT * FROM vu_emp_dept;
```

### 利用视图对数据进行格式化

- 我们经常需要输出某个格式的内容，比如我们想输出员工姓名和对应的部门名，对应格式为emp_name(department_name)，就可以使用视图来完成数据格式化的操作：

```sql
#利用视图对数据进行格式化
CREATE VIEW vu_emp_dept1
AS
SELECT CONCAT(e.last_name,'(',d.department_name,')') emp_info
FROM emps e JOIN depts d
ON e.`department_id` = d.`department_id`;

SELECT * FROM vu_emp_dept1;
```

### 基于视图创建视图

- 当我们创建好一张视图之后，还可以在它的基础上继续创建视图。

```sql
#2.3 基于视图创建视图
CREATE VIEW vu_emp4
AS
SELECT employee_id,last_name
FROM vu_emp1;

SELECT * FROM vu_emp4; 
```

## 查看视图

```sql
#3. 查看视图
# 语法1：查看数据库的表对象、视图对象
SHOW TABLES;

#语法2：查看视图的结构
DESCRIBE vu_emp1;
DESC vu_emp1;

#语法3：查看视图的属性信息
SHOW TABLE STATUS LIKE 'vu_emp1';

#语法4：查看视图的详细定义信息
-- 包括SQL语句
SHOW CREATE VIEW vu_emp1;
```

## 更新视图的数据

### 一般情况

- MySQL支持使用INSERT、UPDATE和DELETE语句对视图中的数据进行插入、更新和删除操作。当视图中的数据发生变化时，数据表中的数据也会发生变化，反之亦然。

```sql
#4."更新"视图中的数据
#4.1 一般情况，可以更新视图的数据
SELECT * FROM vu_emp1;

SELECT employee_id,last_name,salary
FROM emps;
#更新视图的数据，会导致基表中数据的修改
UPDATE vu_emp1
SET salary = 20000
WHERE employee_id = 101;

#同理，更新表中的数据，也会导致视图中的数据的修改
UPDATE emps
SET salary = 10000
WHERE employee_id = 101;

#删除视图中的数据，也会导致表中的数据的删除
DELETE FROM vu_emp1
WHERE employee_id = 101;

SELECT employee_id,last_name,salary
FROM emps
WHERE employee_id = 101;
```

### 不可更新的视图

>虽然可以更新视图数据，但总的来说，视图作为 虚拟表 ，主要用于 方便查询 ，不建议更新视图的数据。**对视图数据的更改，都是通过对实际数据表里数据的操作来完成的。**

- 要使视图可更新，视图中的行和底层基本表中的行之间必须存在 **一对一** 的关系。另外当视图定义出现如下情况时，视图**不支持**更新操作：
  - 在定义视图的时候指定了“ALGORITHM = TEMPTABLE”，视图将不支持INSERT和DELETE操作；
  - 视图中不包含基表中所有被定义为非空又未指定默认值的列，视图将不支持INSERT操作；
  - 在定义视图的SELECT语句中使用了 JOIN联合查询 ，视图将不支持INSERT和DELETE操作；
  - 在定义视图的SELECT语句后的字段列表中使用了 数学表达式 或 子查询 ，视图将不支持INSERT，也不支持UPDATE使用了数学表达式、子查询的字段值；
  - 在定义视图的SELECT语句后的字段列表中使用 DISTINCT 、 聚合函数 、 GROUP BY 、 HAVING 、 UNION 等，视图将不支持INSERT、UPDATE、DELETE；
  - 在定义视图的SELECT语句中包含了子查询，而子查询中引用了FROM后面的表，视图将不支持 INSERT、UPDATE、DELETE；
  - 视图定义基于一个 不可更新视图 ；
  - 常量视图。





## 修改视图

```sql
#5. 修改视图
DESC vu_emp1;

#方式1
CREATE OR REPLACE VIEW vu_emp1
AS
SELECT employee_id,last_name,salary,email
FROM emps
WHERE salary > 7000;

#方式2
ALTER VIEW vu_emp1
AS 
SELECT employee_id,last_name,salary,email,hire_date
FROM emps;
```

## 删除视图

- 删除视图只是删除视图的定义，并不会删除基表的数据。
- 基于视图a、b创建了新的视图c（看作a,b select语句的叠加），如果将视图a或者视图b删除，会导致视图c的查询失败。这样的视图c需要手动删除或修改，否则影响使用。

```sql
#6. 删除视图
SHOW TABLES;

DROP VIEW vu_emp4;

DROP VIEW IF EXISTS vu_emp2,vu_emp3;
```

## 视图优缺点

### 优点



### 缺点

















存储过程相当于外面传进去IN OUT，生命周期跟随外界定义的变量



