package mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 自己开发的JDBC工具类，基于Druid
 */
@SuppressWarnings({"all"})
public class JDBCUtils {
    private static DataSource dataSource;

    private JDBCUtils() {
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src\\mhl\\utils\\druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            //将编译异常转化为运行异常，在开发中很常用
            //程序员可以自主选择捕获与否(而编译异常必须被处理)
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接数据库
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭JDBC相关资源.
     * 如果不想关闭某个资源，传入null即可
     *
     * @param resultSet
     * @param statement  Statement或PreparedStatement对象
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
