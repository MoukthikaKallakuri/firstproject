package com.example.firstproject.service;

import com.example.firstproject.model.User;
import com.example.firstproject.security.JwtUtil;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Firestore firestore;
    @Autowired
    private JwtUtil jwtUtil;

    public void register(User user) throws Exception{

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        firestore.collection("users")
                .document(user.getEmail())
                .set(user);
    }
    public String login(String email,String password)throws Exception{
        DocumentSnapshot document=
                firestore.collection("users")
                        .document(email)
                        .get()
                        .get();
        if(!document.exists()){
            throw new RuntimeException("User not found");
        }
        User user = document.toObject(User.class);
        if(!passwordEncoder.matches(
                password,
                user.getPassword()
        )){
            throw new RuntimeException("Invalid password");
        }
         return jwtUtil.generateToken(email);
    }
}
