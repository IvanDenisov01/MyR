package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age SMALLINT)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица users удалена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age){
            String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
            try (Connection connection = Util.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                System.out.println("Пользователь с именем " + name + " добавлен в базу данных.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void removeUserById(long id){
                String sql = "DELETE FROM users WHERE id = ?";
                try (Connection connection = Util.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setLong(1, id);
                    preparedStatement.executeUpdate();
                    System.out.println("Пользователь с id " + id + " удален из базы данных.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

                public List<User> getAllUsers () {
                    List<User> users = new ArrayList<>();
                    String sql = "SELECT * FROM users";
                    try (Connection connection = Util.getConnection();
                         Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(sql)) {
                        while (resultSet.next()) {
                            Long id = resultSet.getLong("id");
                            String name = resultSet.getString("name");
                            String lastName = resultSet.getString("last_name");
                            Byte age = resultSet.getByte("age");
                            users.add(new User(name, lastName, age));
                            users.get(users.size() - 1).setId(id);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return users;
                }

                public void cleanUsersTable () {
                    String sql = "DELETE FROM users";
                    try (Connection connection = Util.getConnection();
                         Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                        System.out.println("Таблица users очищена.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

