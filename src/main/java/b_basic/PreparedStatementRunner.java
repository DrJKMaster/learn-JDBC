package b_basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//解决了 sql 注入风险，推荐使用
//优化了执行速度
//占位符不能用于表名和列名
public class PreparedStatementRunner {
    public static void main(String[] args) throws SQLException {
        DriverLoader.loadDriverManagerSimplified();
        Connection conn = ConnectionLoader.loadConnectionUseDriverManagerSimplified();

        String preparedDML = "insert into test (name, birthday, description) values (?, ?, ?)";
        ArrayList<Object> params1 = new ArrayList<>();
        params1.add("周八");
        params1.add("2005-01-01");
        params1.add("周八是一个好人");
        System.out.println(runDML(conn, preparedDML, params1));

        String preparedDQL = "select * from test where id > ?";
        ArrayList<Object> params2 = new ArrayList<>();
        params2.add(9);
        ResultSet resultSet = runDQL(conn, preparedDQL, params2);
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

        String preparedSQL = "update test set name = ? where id = ?";
        ArrayList<Object> params3 = new ArrayList<>();
        params3.add("周八");
        params3.add(1);
        System.out.println(runSQL(conn, preparedSQL, params3));
    }

    public static int runDML(Connection conn, String preparedDML, ArrayList<Object> params) {
        try {
            //准备 PreparedStatement 对象
            PreparedStatement statement = conn.prepareStatement(preparedDML);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            //执行 DML 语句，返回受影响的行数
            int effectedRows = statement.executeUpdate();
            //释放资源
            statement.close();
            return effectedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet runDQL(Connection conn, String preparedDQL, ArrayList<Object> params) {
        try {
            //准备 PreparedStatement 对象
            PreparedStatement statement = conn.prepareStatement(preparedDQL);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            //执行 DQL 语句，返回结果集
            return statement.executeQuery();
            //释放资源会导致 ResultSet 被清空
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean runSQL(Connection conn, String preparedSQL, ArrayList<Object> params) {
        try {
            //准备 PreparedStatement 对象
            PreparedStatement statement = conn.prepareStatement(preparedSQL);
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            //执行 SQL 语句，返回是否成功
            boolean execute = statement.execute();
            //释放资源
            statement.close();
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
