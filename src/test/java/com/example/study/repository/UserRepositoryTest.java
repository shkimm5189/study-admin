package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.repogitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void create(){
        User user = new User();
        user.setAccount("Test05");
        user.setEmail("kim3@naver.com");
        user.setPhoneNumber("010-3323-123123");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin5");
        User newUser = userRepository.save(user);
        System.out.println("new User" + newUser);
    }
    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(5L);

        user.ifPresent(selectUser -> {

            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println("여기야"+ item);
            });
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
    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);
        if(deleteUser.isPresent()){
            System.out.println("데이터 존재 :" + deleteUser.get());
        } else {
            System.out.println("데이터 삭제 데이터 없음");
        }
    }
}