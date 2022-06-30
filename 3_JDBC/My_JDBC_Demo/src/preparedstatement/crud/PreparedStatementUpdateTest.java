package preparedstatement.crud;/**
 * @author jifengzhiyu
 * @create 2022-06-30 09:27
 */

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 *@ClassName PreparedStatementUpdateTest
 *@Description TODO
 *@Author kaixin
 *@Date 2022/6/30 09:27
 *@Version 1.0
 */
public class PreparedStatementUpdateTest {

    // 向customers表中添加一条记录
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.读取配置文件中的4个基本信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            // 2.加载驱动
            Class.forName(driverClass);

            // 3.获取连接
            conn = DriverManager.getConnection(url, user, password);

//		System.out.println(conn);

            //4.预编译sql语句，返回PreparedStatement的实例
            String sql = "insert into customers(name,email,birth)values(?,?,?)";//?:占位符
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new Date(date.getTime()));

            //6.执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //7.资源的关闭,涉及到资源关闭用try catch finally
            try {
                if(ps != null)//避免空指针
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
