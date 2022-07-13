package b_basic;

import a_info.Info;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionLoader {
    public static void main(String[] args) {
        Driver driver = DriverLoader.loadDriverFromProperties();
        DriverLoader.loadDriverManagerSimplified();

        Connection conn1 = loadConnectionUseDriver(driver);
        Connection conn2 = loadConnectionUseDriverManager();
        Connection conn3 = loadConnectionUseDriverManagerSimplified();
        Connection conn4 = loadConnectionUseDriverManagerBatchUrl();
    }

    //使用 Driver 建立连接
    public static Connection loadConnectionUseDriver(Driver driver) {
        try {
            //建立 properties 对象存储账号密码
            Properties info = new Properties();
            info.setProperty("user", Info.userName);
            info.setProperty("password", Info.password);
            //得到连接
            return driver.connect(Info.url, info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //使用 DriverManager 建立连接
    public static Connection loadConnectionUseDriverManager() {
        try {
            //建立 properties 对象存储账号密码
            Properties info = new Properties();
            info.setProperty("user", Info.userName);
            info.setProperty("password", Info.password);
            //得到连接
            return DriverManager.getConnection(Info.url, info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //使用 DriverManager 建立连接，不使用 properties
    public static Connection loadConnectionUseDriverManagerSimplified() {
        try {
            //得到连接
            return DriverManager.getConnection(Info.url, Info.userName, Info.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection loadConnectionUseDriverManagerBatchUrl() {
        try {
            //得到连接
            return DriverManager.getConnection(Info.batchUrl, Info.userName, Info.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
