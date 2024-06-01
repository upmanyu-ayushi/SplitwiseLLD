package org.bao.services;

import org.bao.models.Expense;
import org.bao.models.Group;
import org.bao.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public List<Expense> getUserExpenses(String userId) {
        return null;
    }

    public List<Expense> getUserBalances(String userId) {
        return null;

    }

    public User createUser(User user) {
        users.add(user);
    }

    public User getUserById(String id) {
        return users.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList()).get(0);
    }


}
