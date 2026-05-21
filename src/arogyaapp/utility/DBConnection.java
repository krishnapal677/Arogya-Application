
//import FrameLogin.LoginScrrenn;
package arogyaapp.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DBConnection {

    private static Connection conn = null;

    static {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("driver loaded successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Aman:1521/XE", "Arogya", "Krishna@0939");
            System.out.println("connection open successfully");
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
                JOptionPane.showMessageDialog(null,"connction  close successfully");
                
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

}
