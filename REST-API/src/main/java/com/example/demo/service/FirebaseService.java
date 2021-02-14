package com.example.demo.service;

import com.example.demo.object.Messages;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.example.demo.object.Person;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    // Saving the user details
    public String saveUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String newUserEmail = person.getEmailAddress();
        // Get collections from DB
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(newUserEmail).set(person);
        return String.format("Document with email address %s has been created [Time: %s]", newUserEmail, collectionsApiFuture.get().getUpdateTime().toString());
    }

    // Retrieve a users details based on a provided email address
    public Person getUserDetails(String emailAddress) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection("users").document(emailAddress);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Person person = null;

        if(document.exists())
        {
            person = document.toObject(Person.class);
        }
        return person;
    }

    // Updates users details
    public String updateUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String userEmail = person.getEmailAddress();
        // Get collections from DB
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(userEmail).set(person);

        return String.format("Document with email address %s has been updated [Time: %s]", userEmail, collectionsApiFuture.get().getUpdateTime().toString());
    }

    // Deletes a user based on their email address
    public String deleteUser(String emailAddress) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(emailAddress).delete();

        return String.format("Document with email address %s has been deleted [Time: %s]", emailAddress, writeResult.get().getUpdateTime().toString());
    }

    // Messages
    // Retrieves chat history between two users
    public Messages getChatHistory(String emailAddress) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection("messages").document(emailAddress);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Messages history = null;

        if(document.exists())
        {
            history = document.toObject(Messages.class);
        }
        return history;
    }

    // Send message to a user
    public String sendMessage(Messages message) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("messages").document(message.getEmailAddress1()).set(message);

        return String.format("Message has been sent! [Time: %s]", collectionsApiFuture.get().getUpdateTime().toString());
    }

    // Replying to messages
    public String updateChatHistory(Messages message, String sender) throws ExecutionException, InterruptedException {
        // Original message history
        Messages currentMessages = getChatHistory(message.getEmailAddress1());

        String runningHistory = currentMessages.getChatHistory();

        Firestore dbFirestore = FirestoreClient.getFirestore();

        currentMessages.setChatHistory(runningHistory + "[" + sender + "]: " + message.getChatHistory());

        // Get collections from DB
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("messages").document(currentMessages.getEmailAddress1()).set(currentMessages);

        return String.format("Message from %s has been sent! [Time: %s]", sender, collectionsApiFuture.get().getUpdateTime().toString());
    }

    // For editing previously sent messages
    public String editChatHistory(Messages message) throws ExecutionException, InterruptedException {
        // Original message history
        Messages currentMessages = getChatHistory(message.getEmailAddress1());

        Firestore dbFirestore = FirestoreClient.getFirestore();

        currentMessages.setChatHistory(message.getChatHistory());

        // Get collections from DB
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("messages").document(currentMessages.getEmailAddress1()).set(currentMessages);

        return String.format("Message has been updated! [Time: %s]", collectionsApiFuture.get().getUpdateTime().toString());
    }

    // Deleting messages between two users
    public String deleteChatHistory(String emailAddress) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("messages").document(emailAddress).delete();

        return String.format("Issue raised by %s has been deleted [Time: %s]", emailAddress, writeResult.get().getUpdateTime().toString());
    }
}
