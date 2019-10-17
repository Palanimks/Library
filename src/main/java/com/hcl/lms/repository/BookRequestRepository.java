package com.hcl.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.lms.entity.BookRequest;

public interface BookRequestRepository extends JpaRepository<BookRequest, Integer> {

}
