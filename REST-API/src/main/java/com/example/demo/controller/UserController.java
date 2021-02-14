package com.example.demo.controller;

import com.example.demo.object.Person;
import com.example.demo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUserDetails")
    public Person getExample(@RequestHeader String emailAddress) throws ExecutionException, InterruptedException {
        return firebaseService.getUserDetails(emailAddress);
    }

    /*
     * Use Body as if we have 100's params
     * it is inconvenient to use @RequestHeader.
     * Spring automatically does type casting for us
     */

    @PostMapping("/createUser")
    public String createUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        // Persist this to DB
        return firebaseService.saveUserDetails(person);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseService.updateUserDetails(person);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader String emailAddress) throws ExecutionException, InterruptedException {
        return firebaseService.deleteUser(emailAddress);
    }

}
