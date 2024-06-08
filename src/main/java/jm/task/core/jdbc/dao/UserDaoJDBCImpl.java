package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.connect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
      try (Statement statement = connection.createStatement()){
          statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGSERIAL PRIMARY KEY, name VARCHAR (255)" +
                  ", last_name VARCHAR (255), age SMALLINT)");
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }

    public void dropUsersTable() {
    try (Statement statement = connection.createStatement()){
        statement.execute("DROP TABLE IF EXISTS users");

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void saveUser(String name, String lastName, byte age) {
    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)")){
       statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setByte(3, age);
        statement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


    }

    public void removeUserById(long id) {
    try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")){
        statement.setLong(1, id);
       if (statement.executeUpdate() > 0){
           System.out.println("User with id " + id + " removed");
        }else {
           System.out.println("User with id " + id + " not find");
       }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
       try(Statement statement = connection.createStatement()){
           try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")){
              while (resultSet.next()){
                  Long id = resultSet.getLong("id");
                  String name = resultSet.getString("name");
                  String lastName  = resultSet.getString("last_name");
                  Byte age  = resultSet.getByte("age");

                 User user = new User(id, name, lastName, age);
                 users.add(user);
              }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE users RESTART identity CASCADE");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
