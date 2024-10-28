package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, Integer> {

}
