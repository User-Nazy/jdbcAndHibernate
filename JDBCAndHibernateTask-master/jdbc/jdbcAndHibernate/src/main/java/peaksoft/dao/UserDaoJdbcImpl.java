package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {

        String CreateTable = " CREATE TABLE if not exists users ( id Serial primary key ," +
                " name varchar  not null ," +
                " last_name varchar not null, " +
                "age int not null)";
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate((CreateTable));
            System.out.println(" table created!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {

        String DropUsersTable = "DROP TABLE if exists users";
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate((DropUsersTable));
            System.out.println(" table deleted !!! ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void saveUser(  String name, String lastName, byte age) {

        String SaveUser = "INSERT INTO users ( name , last_name , age  ) values (?, ?, ?)";
            try (Connection conn = Util.connect();
                 PreparedStatement statement = conn.prepareStatement(SaveUser,Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.setString( 2,lastName);
                statement.setDouble(3, age);
                statement.executeUpdate();
                System.out.println( name + " added to the database");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



    public void removeUserById(long id) {

        String RemoveUserByID = "DELETE FROM users where id = ?";
        try ( Connection conn = Util.connect();
        PreparedStatement statement = conn.prepareStatement(RemoveUserByID )){
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println( id +  " deleted!!!  ");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User>userList  = new ArrayList<>();
        String GetAllUser = "SELECT * FROM users" ;
        try ( Connection conn = Util.connect();
        PreparedStatement statement = conn.prepareStatement(GetAllUser);
        ResultSet resultSet = statement.executeQuery()){
    User user;
    while (resultSet.next()){
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAge(resultSet.getByte("age"));
        userList.add(user);
    }
            }catch ( SQLException e){
                System.out.println(e.getMessage());
            } return userList;

        }


        public void cleanUsersTable() {

        String CleanUserTable = " truncate users";
        try ( Connection conn = Util.connect();
        Statement statement = conn.createStatement()){
            statement.executeUpdate(CleanUserTable);
                System.out.println(  " table users cleaning !!! ");
            }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}