package b_basic;

import a_info.Info;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverLoader {
    public static void main(String[] args) {
        Driver driver1 = loadDriver();
        Driver driver2 = loadDriverFromProperties();
        loadDriverManager();
        loadDriverManagerSimplified();
    }

    //静态加载驱动，返回 Driver 类
    public static Driver loadDriver() {
        try {
            //加载驱动
            return new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //动态加载驱动，返回 Driver 类
    public static Driver loadDriverFromProperties() {
        //加载驱动
        try {
            Class<?> driverClass = Class.forName(Info.driverName);
            return (Driver) driverClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //动态注册驱动到 DriverManager 类
    public static void loadDriverManager() {
        try {
            //加载驱动
            Class<?> driverClass = Class.forName(Info.driverName);
            Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
            //注册驱动
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //动态注册驱动到 DriverManager 类
    //只需要 loadClass：在 com.mysql.cj.jdbc.Driver 的静态代码块中已完成注册操作
    public static void loadDriverManagerSimplified() {
        try {
            Class.forName(Info.driverName);
            //高版本 mysql 驱动会自动注册，无需手动加载（即该句也可省略），但建议手动加载
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
