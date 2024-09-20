package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
   public User registerUser(User user) {
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);

   }

}
