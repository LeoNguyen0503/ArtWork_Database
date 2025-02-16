import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        Connection conn1 = null;
        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");


            String dbURL1 = "jdbc:oracle:thin:n83nguye/03050264@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database

            conn1 = DriverManager.getConnection(dbURL1);      //Connect to the school database
            if (conn1 != null) {
                System.out.println("Connected with connection #1");
                OracleGUI userGUI = new OracleGUI(conn1);//check if the connection is successful
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
