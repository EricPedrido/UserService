package com.datacom.userservice.server.api.repository;

import com.datacom.userservice.server.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SoapUserRepository {
    private static final Map<String, User> users = new HashMap<>();

    public User getUser(String email) {
        return users.get(email);
    }

    public void postUser(User user) {
        users.put(user.getEmail(), user);
    }
}
