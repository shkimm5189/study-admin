package com.example.study.repository;

import com.example.study.StudyApplication;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.repogitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class userRepositoryTest extends StudyApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void create(){
        User user = new User();
        user.setAccount("Test03");
        user.setEmail("kim3@naver.com");
        user.setPhoneNumber("010-333-123123");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin2");
        User newUser = userRepository.save(user);
        System.out.println("new User" + newUser);
    }
    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            System.out.println(selectUser);
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("updateMethod()");

            userRepository.save(selectUser);
        });
    }
    
    public void delete(){

    }
}
