package c_feature;

import b_basic.ConnectionLoader;
import b_basic.DriverLoader;

import java.sql.Connection;
import java.sql.SQLException;

//JDBC 默认自动提交事务
public class Transaction {
    public static void main(String[] args) throws SQLException {
        DriverLoader.loadDriverManagerSimplified();
        Connection conn = ConnectionLoader.loadConnectionUseDriverManagerSimplified();

        //开启事务
        conn.setAutoCommit(false);
        //提交事务
        conn.commit();
        //回滚事务
        conn.rollback();
        //关闭事务
        conn.setAutoCommit(true);
    }
}
