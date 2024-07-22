package com.project.Library_Management_Spring_BackEnd.repository;

import com.project.Library_Management_Spring_BackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String s);
}
