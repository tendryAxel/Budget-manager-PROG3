package operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    static Connection connection;
    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(
                        System.getenv("PG_URL"),
                        System.getenv("PG_USER"),
                        System.getenv("PG_PASSWORD")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    };
}
