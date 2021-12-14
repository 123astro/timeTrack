package com.timeTrack.timeTrack.controllers;

import com.timeTrack.timeTrack.models.AppUser;
import com.timeTrack.timeTrack.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    AppUser getUser(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody AppUser updates, @PathVariable Long id) {
        AppUser user = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updates.getUsername() != null) user.setUsername(updates.getUsername());
        return new ResponseEntity<>(userRepo.save(updates), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepo.deleteById(id);
    }


}
