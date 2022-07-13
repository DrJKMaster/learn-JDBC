package b_basic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//存在 sql 注入风险，在任何情况下都不推荐使用
public class StatementRunner {
    public static void main(String[] args) throws SQLException {
        DriverLoader.loadDriverManagerSimplified();
        Connection conn = ConnectionLoader.loadConnectionUseDriverManagerSimplified();

        String DML = "insert into test (name, birthday, description) values ('周八', '2005-01-01', '周八是一个好人')";
        System.out.println(runDML(conn, DML));

        String DQL = "select * from test where id > 9";
        ResultSet resultSet = runDQL(conn, DQL);
        while (resultSet.next()) {
            //参数填字段位置（从 1 开始）或字段名，推荐使用字段名
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String description = resultSet.getString("description");
            System.out.println(id + "\t" + name + "\t" + birthday + "\t" + description);
        }
        //释放资源
        resultSet.close();

        String SQL = "update test set name = '周八' where id = 1";
        System.out.println(runSQL(conn, SQL));
    }

    public static int runDML(Connection conn, String DML) {
        try {
            //准备 Statement 对象
            Statement statement = conn.createStatement();
            //执行 DML 语句，返回受影响的行数
            int effectedRows = statement.executeUpdate(DML);
            //释放资源
            statement.close();
            return effectedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet runDQL(Connection connection, String DQL) {
        try {
            //准备 Statement 对象
            Statement statement = connection.createStatement();
            //执行 DQL 语句，返回结果集
            return statement.executeQuery(DQL);
            //释放资源会导致 ResultSet 被清空
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean runSQL(Connection connection, String SQL) {
        try {
            //准备 Statement 对象
            Statement statement = connection.createStatement();
            //执行 SQL 语句，返回是否成功
            boolean execute = statement.execute(SQL);
            //释放资源
            statement.close();
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
