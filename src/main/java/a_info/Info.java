package a_info;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Info {
    public static final String driverName;
    public static final String url;
    public static final String batchUrl;
    public static final String userName;
    public static final String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/mysql.properties"));
            driverName = properties.getProperty("driver");
            url = properties.getProperty("url");
            batchUrl = properties.getProperty("batchUrl");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(driverName);
        System.out.println(url);
        System.out.println(batchUrl);
        System.out.println(userName);
        System.out.println(password);
    }
}
