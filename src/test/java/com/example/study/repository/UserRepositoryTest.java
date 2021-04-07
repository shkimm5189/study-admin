package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.repogitory.UserRepository;
import org.junit.jupiter.api.Assertions;
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
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "01011113333";
        LocalDateTime registeredAt = LocalDateTime.now();


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();
        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }
    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("01051890829");

        user.getOrderGroupList().forEach(orderGroup -> {
            System.out.println("----------------");
            System.out.println(orderGroup.getTotalPrice());
            System.out.println(orderGroup.getRevAddress());
            System.out.println(orderGroup.getRevName());
            System.out.println(orderGroup.getTotalQuantity());

            System.out.println("----------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println(orderDetail.getItem().getPartner().getName());
                System.out.println(orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println(orderDetail.getItem().getName());
                System.out.println(orderDetail.getItem().getPartner().getCallCenter());
                System.out.println(orderDetail.getStatus());
                System.out.println(orderDetail.getArrivalDate());


            });
        });
        Assertions.assertNotNull(user);
    }


}
