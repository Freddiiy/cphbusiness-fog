package persistance;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String USER;
    private final String PASSWORD;
    private final String URL;
    private static final Database instance = new Database();
    private static final ConnectionPool pool = new ConnectionPool(new Database(), 2);

    public Database(String user, String password, String url) {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null) {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        } else {
            USER = user;
            PASSWORD = password;
            URL = url;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Database() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null) {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        } else {
            Dotenv dotenv = Dotenv.configure().load();

            USER = dotenv.get("USER");
            PASSWORD = dotenv.get("PASSWORD");
            URL = dotenv.get("URL");
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static Database getInstance() {
        return instance;
    }

    public static ConnectionPool getPool() {
        return pool;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getURL() {
        return URL;
    }
}