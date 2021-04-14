package com.datacom.userservice.server.api;

import com.datacom.userservice.server.api.repository.UserRepository;
import com.datacom.userservice.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User getUserById(String email) {
        return repository.findById(email).orElse(null);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void update(String email, User newUser) {
        repository.findById(email)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return repository.save(user);
                });
    }

    public void delete(String email) {
        repository.deleteById(email);
    }
}
