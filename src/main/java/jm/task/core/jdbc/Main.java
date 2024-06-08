package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
       userService.saveUser("Мария", "Тимкина", (byte) 45);
      // userService.saveUser("Тимофей", "Петров", (byte) 25);
        //userService.saveUser("Максим", "Красоткин", (byte) 30);
      // userService.saveUser("Наталья", "Иванова", (byte) 68);

        //userService.removeUserById(2);
        userService.getAllUsers().forEach(System.out::println);
        //userService.cleanUsersTable();



    }
}
