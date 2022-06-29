package connnection;/**
 * @author jifengzhiyu
 * @create 2022-06-28 22:41
 */

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 *@ClassName ConnectionTest
 *@Description TODO
 *@Author kaixin
 *@Date 2022/6/28 22:41
 *@Version 1.0
 */
public class ConnectionTest {
    // 方式一：
    @Test
    public void testConnection1() throws SQLException {
        // 获取Driver实现类对象
        //mysql5的
//        Driver driver = new com.mysql.jdbc.Driver();
        //mysql8的
        Driver driver = new com.mysql.cj.jdbc.Driver();

        // url:http://localhost:8080/gmall/keyboard.jpg
        // jdbc:mysql:协议
        // localhost:ip地址
        // 3306：默认mysql的端口号
        // test:test数据库
        String url = "jdbc:mysql://localhost:3306/test";
//        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
        // 将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "kaixin61118");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    // 方式二：对方式一的迭代:在如下的程序中不出现第三方的api,使得程序具有更好的可移植性
    @Test
    public void testConnection2() throws Exception {
        // 1.获取Driver实现类对象：使用反射
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        // 2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 3.提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "kaixin61118");

        // 4.获取连接
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }
}
