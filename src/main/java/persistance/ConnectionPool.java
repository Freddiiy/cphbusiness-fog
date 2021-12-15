package persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ConnectionPool {
    private int maxPoolSize = 10;
    private int connectionNum = 0;
    private Database database;
    private static final String SQL_VERIFYCONN = "select 1";

    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();

    public ConnectionPool(Database database, int maxPoolSize) {
        this.database = database;
        this.maxPoolSize = maxPoolSize;
    }

    public synchronized Connection getConnection() {
        try {
            
        Connection connection = null;

        if(isFull()) {
            connection = createNewConnectionForPool();
        }

        connection = getConnectionFromPool();

        if (connection == null) {
            connection = createNewConnectionForPool();
        }

        connection = makeAvailable(connection);
        return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public synchronized void returnConnection(Connection connection)
            throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(connection)) {
            throw new SQLException(
                    "The connection is returned already or it isn't for this pool");
        }
        freePool.push(connection);
    }

    private Connection createNewConnection() throws SQLException {
        Connection connection = null;
        connection = Database.getInstance().connect();
        return connection;
    }

    private Connection createNewConnectionForPool() throws SQLException {
        Connection connection = createNewConnection();
        connectionNum++;
        occupiedPool.add(connection);
        return connection;
    }

    private Connection getConnectionFromPool() {
        Connection connection = null;
        if (freePool.size() > 0) {
            connection = freePool.pop();
            occupiedPool.add(connection);
        }
        return connection;
    }

    private Connection makeAvailable(Connection connection) throws SQLException {
        if (isConnectionAvailable(connection)) {
            return connection;
        }

        // If the connection is't available, reconnect it.
        occupiedPool.remove(connection);
        connectionNum--;
        connection.close();

        connection = createNewConnection();
        occupiedPool.add(connection);
        connectionNum++;
        return connection;
    }

    private boolean isConnectionAvailable(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.executeQuery(SQL_VERIFYCONN);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private synchronized boolean isFull() {
        return ((freePool.size() == 0) && (connectionNum >= maxPoolSize));
    }
}