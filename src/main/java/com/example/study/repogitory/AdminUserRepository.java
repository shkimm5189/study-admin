package com.example.study.repogitory;

import com.example.study.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
}
