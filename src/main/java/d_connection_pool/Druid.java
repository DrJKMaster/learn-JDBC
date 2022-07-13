package d_connection_pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class Druid {
    public static void main(String[] args) {
        DataSource source = getDataSource();
    }

    public static DataSource getDataSource() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/druid.properties"));
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
