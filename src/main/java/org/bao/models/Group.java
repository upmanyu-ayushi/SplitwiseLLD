package org.bao.models;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Group {

    private String id;
    private String name;
    private List<User> users;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }
}
