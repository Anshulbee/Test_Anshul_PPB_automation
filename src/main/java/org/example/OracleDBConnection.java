package org.example;
import java.sql.*;

public class OracleDBConnection {
    private static Connection conn = null;

    public void createConnection() throws SQLException {
// JDBC URL format for Oracle: jdbc: oracle: thin: @host:port: SID or ServiceName
        String jdbcUrl = "jdbc: oracle: thin: @//10.100.9.43:3164/CBSUAT";
        String username = "paytmdev";
        String password = "paytmdev4";
        int connectionCount = 0;
// return "jdbc: oracle: thin:@"+"//"+"10.100.9.43"
//Connection connection = null;"3164"+"/"+ "CBSUAT";
        try {
// Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
// Establish the connection
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Opening DB connection...");
            System.out.println("Connected to Oracle Database successfully!");
            connectionCount++;
            System.out.println("Connection count =" + connectionCount);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console.");
            e.printStackTrace();
        }
    }
        public Connection getConn () {
            return conn;
        }
        public void CloseConnection() throws SQLException
        {
            try {
                conn.close();
                System.out.println("Closing DB connection...");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
}

