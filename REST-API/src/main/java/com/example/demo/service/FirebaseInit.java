package com.example.demo.service;
import java.io.FileInputStream;
import java.io.IOException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;

import javax.annotation.PostConstruct;

@Service
public class FirebaseInit {

    @PostConstruct
    public void init() throws IOException {

            FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://rest-api-331a8-default-rtdb.firebaseio.com")
                    .build();

                    FirebaseApp.initializeApp(options);
    }
}
