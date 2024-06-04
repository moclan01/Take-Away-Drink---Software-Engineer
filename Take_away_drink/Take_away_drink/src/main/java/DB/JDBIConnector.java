package DB;

import com.mysql.cj.jdbc.MysqlDataSource;
import model.Account;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBIConnector {
    public static Jdbi jdbi;

    public static void connect() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/b2test");
        dataSource.setUser("root");
        dataSource.setPassword("");
        try {
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       jdbi =  Jdbi.create(dataSource);
    }
    public static Jdbi getJdbi() throws SQLException {
        if(jdbi == null){
            connect();
        }
        return jdbi;
    }



    public static void main(String[] args) throws SQLException {

    }


}
