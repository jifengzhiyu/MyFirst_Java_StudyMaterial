# 变量、流程控制与游标

## 变量

- 在MySQL数据库的存储过程和函数中，可以使用变量来存储查询或计算的中间结果数据，或者输出最终的结果数据。
- 在 MySQL 数据库中，变量分为 **系统变量** 以及 **用户自定义变量** 。 

### 系统变量

- 变量由系统定义，不是用户定义，属于 服务器 层面。启动MySQL服务，生成MySQL服务实例期间，MySQL将为MySQL服务器内存中的系统变量赋值，这些系统变量定义了当前MySQL服务实例的属性、特征。
- 系统变量分为全局系统变量（需要添加 global 关键字）以及会话系统变量（需要添加 session 关键字），有时也把全局系统变量简称为全局变量，有时也把会话系统变量称为local变量。**如果不写，默认****会话级别。**静态变量（在 MySQL 服务实例运行期间它们的值不能使用 set 动态修改）属于特殊的全局系统变量。

![image-20220627092844502](Pic/image-20220627092844502.png)

- 全局系统变量针对于所有会话（连接）有效，但 不能跨重启
- 会话系统变量仅针对于当前会话（连接）有效。会话期间，当前会话对某个会话系统变量值的修改，不会影响其他会话同一个会话系统变量的值。会话1对某个全局系统变量值的修改会导致会话2中同一个全局系统变量值的修改。
- 在MySQL中有些系统变量只能是全局的，例如 max_connections 用于限制服务器的最大连接数；有些系统变量作用域既可以是全局又可以是会话，例如 character_set_client 用于设置客户端的字符集；有些系统变量的作用域只能是当前会话，例如 pseudo_thread_id 用于标记当前会话的 MySQL 连接 ID。 

#### 查看修改系统变量

```sql
-- 查看所有或部分系统变量
#查看所有全局变量 
SHOW GLOBAL VARIABLES;
#查看所有会话变量 
SHOW SESSION VARIABLES;
或
SHOW VARIABLES;

#查看满足条件的部分系统变量。
SHOW GLOBAL VARIABLES LIKE '%标识符%';
#查看满足条件的部分会话变量 
SHOW SESSION VARIABLES LIKE '%标识符%';

-- 查看指定系统变量
-- 作为 MySQL 编码规范，MySQL 中的系统变量以 两个“@” 开头，其中“@@global”仅用于标记全局系统变量，“@@session”仅用于标记会话系统变量。“@@”首先标记会话系统变量，如果会话系统变量不存在，则标记全局系统变量。
#查看指定的系统变量的值
SELECT @@global.变量名;
#查看指定的会话变量的值 
SELECT @@session.变量名;
#或者
SELECT @@变量名;
```

```sql
-- 修改系统变量的值
方式1：修改MySQL 配置文件 ，继而修改MySQL系统变量的值（该方法需要重启MySQL服务）--> 一劳永逸
方式2：在MySQL服务运行期间，使用“set”命令重新设置系统变量的值

-- #针对于当前的数据库实例是有效的，一旦重启mysql服务，就失效了。
#为某个系统变量赋值 
#方式1：
SET @@global.变量名=变量值;
#方式2：
SET GLOBAL 变量名=变量值;

#为某个会话变量赋值 
#针对于当前会话是有效的，一旦结束会话，重新建立起新的会话，就失效了。
#方式1：
SET @@session.变量名=变量值;
#方式2：
SET SESSION 变量名=变量值;
```

### 用户变量

- 用户变量是用户自己定义的，作为 MySQL 编码规范，MySQL 中的用户变量以 一个“@” 开头。根据作用范围不同，又分为 **会话用户变量** 和 **局部变量** 。

  - 会话用户变量：作用域和会话变量一样，只对 当前 **连接** 会话有效。

  - 局部变量：只在 BEGIN 和 END 语句块中有效。局部变量只能在 存储过程和函数 中使用。

#### 初始化问题

```sql
SELECT @big; 
#会话用户变量不用声明数据类型
#查看某个未声明的会话用户变量时，将得到NULL值
```

```sql
-- 局部变量如果没有DEFAULT子句，初始值为NULL
-- 需要指定数据类型
```

#### 会话用户变量

```sql
-- 变量的定义
#方式1：“=”或“:=” 
SET @用户变量 = 值;
SET @用户变量 := 值;
#方式2：“:=” 或 INTO关键字
SELECT @用户变量 := 表达式 
[FROM 等子句];

SELECT 表达式 INTO @用户变量 
[FROM 等子句];

#方式1：
SET @m1 = 1;
SET @m2 := 2;
SET @sum := @m1 + @m2;
-- 下面两个都可以
SELECT @sum;
-- SELECT @m1 + @m2;

#方式2：
SELECT @count := COUNT(*) FROM employees;
SELECT @count;

#方式3：
SELECT AVG(salary) INTO @avg_sal FROM employees;
SELECT @avg_sal;
```

```sql
-- 查看用户变量的值
SELECT @用户变量
```

#### 局部变量

- 定义：可以使用 DECLARE 语句定义一个局部变量
- 作用域：仅仅在定义它的 BEGIN ... END 中有效
- 位置：只能放在 BEGIN ... END 中，而且只能放在第一句

```sql
BEGIN

#声明局部变量 
# 如果没有DEFAULT子句，初始值为NULL
DECLARE 变量名1 变量数据类型 [DEFAULT 变量默认值];
DECLARE 变量名2,变量名3,... 变量数据类型 [DEFAULT 变量默认值];
#为局部变量赋值 
SET 变量名1 = 值;
SELECT 值 INTO 变量名2 [FROM 子句];
#查看局部变量的值
SELECT 变量1,变量2,变量3; 

END
```

```sql
-- 变量赋值
-- 方式1：一般用于赋简单的值
SET 变量名=值; 
SET 变量名:=值;

-- 方式2：一般用于赋表中的字段值
SELECT 字段名或表达式 INTO 变量名 FROM 表
```

```sql
-- 使用变量
SELECT 局部变量名;
```

```sql
#举例：
DELIMITER //
CREATE PROCEDURE test_var()

BEGIN
	#1、声明局部变量
	DECLARE a INT DEFAULT 0;
	DECLARE b INT ;
	#DECLARE a,b INT DEFAULT 0;
	DECLARE emp_name VARCHAR(25);
	#2、赋值
	SET a = 1;
	SET b := 2;
	SELECT last_name INTO emp_name FROM employees WHERE employee_id = 101;
	#3、使用
	SELECT a,b,emp_name;	
END //

DELIMITER ;
#调用存储过程
CALL test_var();
```

```sql
#举例1：声明局部变量，并分别赋值为employees表中employee_id为102的last_name和salary
DELIMITER //

CREATE PROCEDURE test_pro()
BEGIN
	#声明
	DECLARE emp_name VARCHAR(25);
	DECLARE sal DOUBLE(10,2) DEFAULT 0.0;
	#赋值
	SELECT last_name,salary INTO emp_name,sal
	FROM employees
	WHERE employee_id = 102;
	#使用
	SELECT emp_name,sal;
	
END //

DELIMITER ;
#调用存储过程
CALL test_pro();
```

![image-20220627145831332](Pic/image-20220627145831332.png)

## 定义条件与处理程序

- 定义条件 是事先定义程序执行过程中可能遇到的问题， 处理程序 定义了在遇到问题时应当采取的处理方式，并且保证存储过程或函数在遇到警告或错误时能继续执行。这样可以增强存储程序处理问题的能力，避免程序异常停止运行。
- 说明：定义条件和处理程序在存储过程、存储函数中都是支持的。
- **在存储过程中未定义条件和处理程序，且当存储过程中执行的SQL语句报错时，MySQL数据库会抛出错误，并退出当前SQL逻辑，不再向下继续执行。**

```sql
#错误演示：
DELIMITER //
CREATE PROCEDURE UpdateDataNoCondition()
	BEGIN
		SET @x = 1;
		UPDATE employees SET email = NULL WHERE last_name = 'Abel';
		SET @x = 2;
		UPDATE employees SET email = 'aabbel' WHERE last_name = 'Abel';
		SET @x = 3;
	END //
DELIMITER ;

#调用存储过程
#错误代码： 1048
#Column 'email' cannot be null
CALL UpdateDataNoCondition();
SELECT @x;
-- 1
```

### 定义条件

- 定义条件就是给MySQL中的错误码命名，这有助于存储的程序代码更清晰。它将一个 错误名字 和 指定的 错误条件 关联起来。这个名字可以随后被用在定义处理程序的 DECLARE HANDLER 语句中。
- 定义条件使用DECLARE语句，语法格式如下：

```sql
DECLARE 错误名称 CONDITION FOR 错误码（或错误条件）
```

![image-20220623164135489](Pic/image-20220623164135489.png)

```sql
#2.2 定义条件
#格式：DECLARE 错误名称 CONDITION FOR 错误码（或错误条件）
#举例1：定义“Field_Not_Be_NULL”错误名与MySQL中违反非空约束的错误类型是“ERROR 1048 (23000)”对应。
#方式1：使用MySQL_error_code
DECLARE Field_Not_Be_NULL CONDITION FOR 1048;

#方式2：使用sqlstate_value
DECLARE Field_Not_Be_NULL CONDITION FOR SQLSTATE '23000';
/*
不知道为啥给我说:
1064 - You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'DECLARE Field_Not_Be_NULL CONDITION FOR 1048' at line 5, Time: 0.000000s
*/
```

### 定义处理程序

- 可以为SQL执行过程中发生的某种类型的错误定义特殊的处理程序。定义处理程序时，使用DECLARE语句的语法如下：

```sql
DECLARE 处理方式 HANDLER FOR 错误类型 处理语句
```

- **处理方式**：处理方式有3个取值：CONTINUE、EXIT、UNDO。 
  - CONTINUE ：表示遇到错误不处理，继续执行。
  - EXIT ：表示遇到错误马上退出。
  - UNDO ：表示遇到错误后撤回之前的操作。MySQL中暂时不支持这样的操作。
- **错误类型**（即条件）可以有如下取值：
  - SQLSTATE '字符串错误码' ：表示长度为5的sqlstate_value类型的错误代码； 
  - MySQL_error_code ：匹配数值类型错误代码；
  - 错误名称 ：表示DECLARE ... CONDITION定义的错误条件名称。
  - SQLWARNING ：匹配所有以01开头的SQLSTATE错误代码；
  - NOT FOUND ：匹配所有以02开头的SQLSTATE错误代码；
  - SQLEXCEPTION ：匹配所有没有被SQLWARNING或NOT FOUND捕获的SQLSTATE错误代码；
- **处理语句**：如果出现上述条件之一，则采用对应的处理方式，并执行指定的处理语句。语句可以是像“ SET 变量 = 值 ”这样的简单语句，也可以是使用 BEGIN ... END 编写的复合语句。

```sql
-- 定义处理程序的几种方式
#方法1：捕获sqlstate_value
DECLARE CONTINUE HANDLER FOR SQLSTATE '42S02' SET @info = 'NO_SUCH_TABLE';

#方法2：捕获mysql_error_value
DECLARE CONTINUE HANDLER FOR 1146 SET @info = 'NO_SUCH_TABLE';

#方法3：先定义条件，再调用
DECLARE no_such_table CONDITION FOR 1146;
DECLARE CONTINUE HANDLER FOR no_such_table SET @info = 'NO_SUCH_TABLE';

#方法4：使用SQLWARNING
DECLARE EXIT HANDLER FOR SQLWARNING SET @info = 'ERROR';

#方法5：使用NOT FOUND
DECLARE EXIT HANDLER FOR NOT FOUND SET @info = 'NO_SUCH_TABLE';

#方法6：使用SQLEXCEPTION
DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @info = 'ERROR';
```











![image-20220625180439382](Pic/image-20220625180439382.png)

