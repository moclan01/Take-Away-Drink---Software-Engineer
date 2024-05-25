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


//    public  List<User> getAllUsers() throws SQLException{
//        List<User> users = (List<User>) DataConnection.getJdbi().withHandle(handle -> {
//
//            return handle.createQuery("select * from user").mapToBean(User.class).list();
//        });
//        return users;
//    }
    public List<Account> getAllUsers() throws SQLException {
        List<Account> users = (List<Account>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from user").mapToBean(Account.class).list();
        });
        return users;
    }
    public Account getUser(String username, String password) throws SQLException {
        for (Account us : getAllUsers()){
            if(us.getUsername().equalsIgnoreCase(username)&& us.getPassword().equalsIgnoreCase(password)){
                return us;
            }
        }
        return null;
    }
    public Account getUserByUsername(String username) throws SQLException {
        for (Account user: getAllUsers()){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
    public Account getUserByUsernameAndPassword(String username, String password) throws SQLException {
        for (Account user: getAllUsers()){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
        }
        return null;
    }
//    public boolean insertUser(String username, String nickname, String password) throws SQLException{
//        DataConnection.getJdbi().useHandle(handle -> {
//            handle.createUpdate("INSERT INTO user (username, nickname,password) VALUES (?,?,?)")
//                    .bind(0, username)
//                    .bind(1, nickname)
//                    .bind(2, password)
//                    .execute();
//        });
//        return false;
//    }
    public boolean insertUser(String username, String nickname, String password) throws SQLException {
        JDBIConnector.getJdbi().useHandle(handle -> {
            handle.createUpdate("Insert  into user(username, nickname, password) values(?,?,?)")
                    .bind(0,username).bind(1,nickname).bind(2,password).execute();
        });
        return true;
    }

    public boolean deleteUser(String username) throws SQLException{
        JDBIConnector.getJdbi().useHandle(handle -> {
            handle.createUpdate("Delete from user where username = ?").bind(0,username).execute();
        });
        return true;
    }

    public ArrayList<String> getAllUsernames() throws SQLException{
        ArrayList<String> usernames =(ArrayList<String>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select username from user").mapTo(String.class).list();
        });

        return usernames;
    }
    public boolean insert(Account user) {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into user(username,email,password) values(?,?,?)")
                        .bind(0, user.getUsername())
                        .bind(1,user.getEmail())
                        .bind(2,user.getPassword()).execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static void main(String[] args) throws SQLException {
//
    Account user = new Account("admin2","admin","admin");

    System.out.println(new JDBIConnector().insert(user));
    }


}
