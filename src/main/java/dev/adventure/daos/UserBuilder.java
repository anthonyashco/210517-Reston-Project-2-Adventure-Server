package dev.adventure.daos;

import dev.adventure.entities.User;

public class UserBuilder {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        User user = new User(0, "Joseph", "Software Pleb", "Joseph", "Keller", 1);
        userDao.createUser(user);
        System.out.println(user);
    }

}
