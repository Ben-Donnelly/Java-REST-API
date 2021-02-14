package com.example.demo.controller;
import com.example.demo.object.Messages;
import com.example.demo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MessagesController {

    @Autowired
    FirebaseService firebaseService;

    // Sending a message
    @PostMapping("/sendMessage")
    public String createMessage(@RequestBody Messages message) throws ExecutionException, InterruptedException {
        return firebaseService.sendMessage(message);
    }

    // Retrieving the current messages between 2 people
    @GetMapping("/getChatHistory")
    public Messages getChatHistory(@RequestHeader String emailAddress) throws ExecutionException, InterruptedException {
        return firebaseService.getChatHistory(emailAddress);
    }

    /*
     * Replying to messages
     * Requires Header (person who is sending the messages email)
     * and Body (Messages type) parameters
     */
    @PutMapping("/updateChatHistory")
    public String updateUser(@RequestBody Messages message, @RequestHeader String sender) throws ExecutionException, InterruptedException {
        return firebaseService.updateChatHistory(message, sender);
    }

    /*
     * Could just use getChatHistory
     * but I like the integrity.
     * First get the message
     */
    @GetMapping("/editMessage")
    public Messages editMessage(@RequestHeader String emailAddress) throws ExecutionException, InterruptedException {
        return firebaseService.getChatHistory(emailAddress);
    }

    // Then override with PUT
    @PutMapping("/editMessage")
    public String editMessage(@RequestBody Messages message) throws ExecutionException, InterruptedException {
        return firebaseService.editChatHistory(message);
    }

    // Deletes messages with specified user
    @DeleteMapping("/deleteMessages")
    public String deleteChatHistory(@RequestHeader String emailAddressToDelete) throws ExecutionException, InterruptedException {
        return firebaseService.deleteChatHistory(emailAddressToDelete);
    }
}

