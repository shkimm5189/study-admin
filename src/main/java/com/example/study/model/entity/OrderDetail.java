package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@ToString(exclude = {"user","item"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime orderAt;

    // N : 1
    @ManyToOne
    private User user;

    // N : 1
    @ManyToOne
    private Item item;
}
