package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
