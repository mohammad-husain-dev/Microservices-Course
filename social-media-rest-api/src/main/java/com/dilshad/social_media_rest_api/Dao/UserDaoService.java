package com.dilshad.social_media_rest_api.Dao;

import com.dilshad.social_media_rest_api.beans.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usercount=0;
    static {
        users.add(new User(++usercount, "Mohammad", LocalDate.now().minusYears(30)));
        users.add(new User(++usercount, "Davis", LocalDate.now().minusYears(33)));
        users.add(new User(++usercount, "Husain", LocalDate.now().minusYears(27)));
    }
    public List<User> finaAllUsers() {
        return users;
    }

    public User findOne(int id) {
        Predicate<User> predicate = u -> u.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++usercount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        Predicate<User> predicate = u -> u.getId() == id;
        users.removeIf(predicate);
    }
}
