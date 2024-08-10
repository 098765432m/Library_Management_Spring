package com.project.Library_Management_Spring_BackEnd.repository;

import com.project.Library_Management_Spring_BackEnd.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
