package e_db_utils.dao;

import e_db_utils.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

// 泛型 T 指定具体 domain 类型
public class BasicDAO<T> {
    private final QueryRunner qr = new QueryRunner();
    private Connection conn = null;

    // dml
    public int update(String dml, Object... params) {
        try {
            conn = DBUtils.getConnection();
            return qr.update(conn, dml, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.releaseConnection(conn);
        }
    }

    // dql 返回多行多列
    public List<T> queryMRMC(String dql, Class<T> clazz, Object... params) {
        try {
            conn = DBUtils.getConnection();
            return qr.query(conn, dql, new BeanListHandler<>(clazz), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.releaseConnection(conn);
        }
    }

    // dql 返回单行多列
    public T querySRMC(String dql, Class<T> clazz, Object... params) {
        try {
            conn = DBUtils.getConnection();
            return qr.query(conn, dql, new BeanHandler<>(clazz), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.releaseConnection(conn);
        }
    }

    // dql 返回多行单列
    public Object[] queryMRSC(String dql, Object... params) {
        try {
            conn = DBUtils.getConnection();
            return qr.query(conn, dql, new ArrayHandler(), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.releaseConnection(conn);
        }
    }

    // dql 返回单行单列
    public Object querySRSC(String dql, Object... params) {
        try {
            conn = DBUtils.getConnection();
            return qr.query(conn, dql, new ScalarHandler<>(), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.releaseConnection(conn);
        }
    }
}
