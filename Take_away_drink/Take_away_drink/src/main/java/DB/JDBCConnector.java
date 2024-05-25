package DB;

import org.jdbi.v3.core.Jdbi;

import java.sql.*;

import static DB.JDBIConnector.jdbi;

public class JDBCConnector {
    public static Connection getConnection(){
        Connection c = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://localhost:3306/b2test";
            String username = "root";
            String password = "";

            c = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Jdbi getInstance() {
        if (jdbi == null) {
            getConnection();
        }
        return jdbi;
    }
}
