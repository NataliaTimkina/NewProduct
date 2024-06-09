package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Мария", "Тимкина", (byte) 45);
        userService.saveUser("Тимофей", "Петров", (byte) 25);
        userService.saveUser("Максим", "Васильев", (byte) 30);
        userService.saveUser("Наталья", "Иванова", (byte) 68);

        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);

        //userService.removeUserById(2);
        userService.getAllUsers().forEach(System.out::println);
        //userService.cleanUsersTable();

    }
}
