package DB;

import model.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUser extends AbsDao<Account>{

    @Override
    public boolean insert(Account user) {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into user(username,email,password,role) values(?,?,?,?)")
                        .bind(0, user.getUsername())
                        .bind(1,user.getEmail())
                        .bind(2,user.getPassword())
                        .bind(3,user.getRole())
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean delete(Account user) {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("delete from user where username =?").bind(0,user.getUsername()).execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Account> selectAll() throws SQLException {
        List<Account> users = (List<Account>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from user").mapToBean(Account.class).list();
        });
        return users;
    }

    @Override
    public boolean update(Account user) {
        return false;
    }
    public Account getUserByUsername(String username) throws SQLException {
        for (Account user: selectAll()){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
    public boolean deleteUserByUsername(String username) throws SQLException{
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
    public Account getUser(String username, String password) throws SQLException {
        for (Account us : selectAll()){
            if(us.getUsername().equalsIgnoreCase(username)&& us.getPassword().equalsIgnoreCase(password)){
                return us;
            }
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {


        Account acc = new Account("a1","a","a","user");
        //System.out.println(new DaoUser().insert(acc));
        System.out.println(new DaoUser().selectAll());
    }
}
