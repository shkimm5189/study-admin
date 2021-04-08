package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repogitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;


    /*
     toDo
     1. request data
     2. user 생성
     3. 생성된 데이터  -> UserApiResponse return
     */
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        return response(newUser);

    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne,getById
        Optional<User> optional = userRepository.findById(id);
//         user -> userApiResponse return
        return optional
                .map(user -> response(user))
                .orElseGet(
                        ()->Header.ERROR("데이터 없음")
                );

        /*
        람다를 활용하여 더 간단하게 표현
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(
                        () ->Header.ERROR("데이터 없음")
                );
        */

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //todo

        // 1 .data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());
        return optional.map(user-> {
            //3. data -> update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getStatus())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
                //update -> newUser
                .map(user -> userRepository.save(user))
                //userApiResponse
                .map(updateUser -> response(updateUser))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> delete(Long id) {
        return null;
    }

    // user -> userApiResponse
    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화,길이 채크
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getRegisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
