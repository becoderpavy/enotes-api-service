package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
