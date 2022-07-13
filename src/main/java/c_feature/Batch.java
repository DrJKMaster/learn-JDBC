package c_feature;

import b_basic.ConnectionLoader;
import b_basic.DriverLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {
    public static void main(String[] args) throws SQLException {
        DriverLoader.loadDriverManagerSimplified();
        Connection conn1 = ConnectionLoader.loadConnectionUseDriverManagerSimplified();
        Connection conn2 = ConnectionLoader.loadConnectionUseDriverManagerBatchUrl();

        long s1 = System.currentTimeMillis();
        noBatch(conn1);
        long e1 = System.currentTimeMillis();
        System.out.println(e1 - s1);

        long s2 = System.currentTimeMillis();
        useBatch(conn2);
        long e2 = System.currentTimeMillis();
        System.out.println(e2 - s2);

        conn1.close();
        conn2.close();
    }

    public static void noBatch(Connection conn) throws SQLException {
        String preparedDML = "insert into test (name, birthday, description) values (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(preparedDML);
        for (int i = 0; i < 5000; i++) {
            statement.setObject(1, "周八");
            statement.setObject(2, "2005-01-01");
            statement.setObject(3, "周八是一个好人");
            statement.executeUpdate();
        }
        statement.close();
        conn.close();
    }

    public static void useBatch(Connection conn) throws SQLException {
        String preparedDML = "insert into test (name, birthday, description) values (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(preparedDML);
        for (int i = 0; i < 5000; i++) {
            statement.setObject(1, "周八");
            statement.setObject(2, "2005-01-01");
            statement.setObject(3, "周八是一个好人");
            statement.addBatch();
            if ((i + 1) % 1000 == 0) {
                statement.executeBatch();
            }
        }
        statement.close();
        conn.close();
    }
}
