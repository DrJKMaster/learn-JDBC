package e_db_utils.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static final DataSource source;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/druid.properties"));
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
