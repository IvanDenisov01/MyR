package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vanya", "Ivanov", (byte)14);
        userService.saveUser("KOLYA", "Petrov", (byte)15);
        userService.saveUser("Vasya", "Lepov", (byte) 16);
        userService.saveUser("Sasha", "Sahov", (byte)17);
        List<User> usersAfterSave = userService.getAllUsers();
        for (User user : usersAfterSave) {
            System.out.println(user);
        }
        userService.removeUserById(2);
        List<User> usersAfterSave1 = userService.getAllUsers();
        for (User user : usersAfterSave1) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

