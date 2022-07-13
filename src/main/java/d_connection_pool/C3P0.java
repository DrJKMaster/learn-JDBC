package d_connection_pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
    public static void main(String[] args) {
        ComboPooledDataSource source = getDataSource();
    }

    public static ComboPooledDataSource getDataSource() {
        return new ComboPooledDataSource("Pi");
    }
}
