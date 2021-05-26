package com.example.basic2.repository;

import com.example.basic2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    Users findUsersById(int userID);
}
