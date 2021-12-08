/*
package persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int POOL_SIZE = 10;

    public static ConnectionPool init() throws SQLException {
        List<Connection> pool = new ArrayList<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(createConnection());
        }
        return new ConnectionPool();
    }

    public Connection getConnection(){
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection){
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public static Connection createConnection() throws SQLException {
        return new Database().connect();
    }

    public int getPoolSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
*/