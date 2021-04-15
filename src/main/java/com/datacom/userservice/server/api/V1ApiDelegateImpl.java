package com.datacom.userservice.server.api;

import com.datacom.userservice.server.model.Error;
import com.datacom.userservice.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class V1ApiDelegateImpl implements V1ApiDelegate {
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> create(User user, String target) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(user.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> delete(String email, String target) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<User> retrieve(String email, String target) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getUserById(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> update(String email, User user, String target) {
        if (user == null || email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.getUserById(email) == null) {
            return ResponseEntity.notFound().build();
        }

        userService.update(email, user);
        return ResponseEntity.noContent().build();
    }
}
