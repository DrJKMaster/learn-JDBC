package d_connection_pool;

import b_basic.ConnectionLoader;
import b_basic.DriverLoader;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class Test {
    public static void main(String[] args) throws Exception {
        long s, e;

        s = System.currentTimeMillis();
        noPool();
        e = System.currentTimeMillis();
        System.out.println(e - s);

        s = System.currentTimeMillis();
        useC3P0();
        e = System.currentTimeMillis();
        System.out.println(e - s);

        s = System.currentTimeMillis();
        useDruid();
        e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    private static void noPool() throws SQLException {
        DriverLoader.loadDriverManagerSimplified();
        for (int i = 0; i < 5000; i++) {
            Connection connection = ConnectionLoader.loadConnectionUseDriverManagerSimplified();
            connection.close();
        }
    }

    private static void useC3P0() throws SQLException {
        ComboPooledDataSource source = C3P0.getDataSource();
        for (int i = 0; i < 5000; i++) {
            Connection connection = source.getConnection();
            connection.close();
        }
    }

    private static void useDruid() throws Exception {
        DataSource source = Druid.getDataSource();
        for (int i = 0; i < 5000; i++) {
            Connection connection = source.getConnection();
            connection.close();
        }
    }
}
