package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Атабек", "Исакунов", (byte) 20);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        User user2 = new User("Бурул", "Исмаилова", (byte) 36);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        User user3 = new User("Элнура", "Таджибаева", (byte) 25);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        User user4 = new User("Муктарбек", "Кубанов", (byte) 22);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        User user5 = new User("Улукбек", "Юнусов", (byte) 29);
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());

        List<User> userList = userService.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }

        userService.removeUserById(1);
        userService.cleanUsersTable();


        Util.shutDown();
    }
}

