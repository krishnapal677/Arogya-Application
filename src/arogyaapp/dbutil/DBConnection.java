package arogyaapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn = null;

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL driver loaded successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            // ✅ Correct DB name + credentials
            String url = "jdbc:mysql://localhost:3306/Arogya?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "Arogya";
            String password = "arogya123";   // MUST match MySQL password

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL connection opened successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("MySQL connection closed successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
