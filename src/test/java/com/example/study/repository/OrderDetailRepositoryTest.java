package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.repogitory.OrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();




        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
    }
}
