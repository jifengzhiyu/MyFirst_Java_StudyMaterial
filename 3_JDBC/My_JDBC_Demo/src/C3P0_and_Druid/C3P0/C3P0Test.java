package C3P0_and_Druid.C3P0;
/**
 * @author jifengzhiyu
 * @create 2022-07-02 18:50
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName C3P0Test
 * @Description TODO
 * @Author kaixin
 * @Date 2022/7/2 18:50
 * @Version 1.0
 */

public class C3P0Test {
    //官网:
    //https://sourceforge.net/projects/c3p0/
    //lib导入官网下载的两个.jar文件
    //方式一：
    @Test
    public void testGetConnection() throws Exception {
        //获取c3p0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        //MySQL8写法
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        cpds.setUser("root");
        cpds.setPassword("123456789");
        //通过设置相关的参数，对数据库连接池进行管理：
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        //销毁c3p0数据库连接池
        //一般不会关连接池
//		DataSources.destroy( cpds );
    }

    //方式二：使用配置文件
    @Test
    public void testGetConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("hellc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}