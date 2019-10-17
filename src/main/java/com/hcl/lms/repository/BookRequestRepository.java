package com.hcl.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcl.lms.entity.BookRequest;

public interface BookRequestRepository extends JpaRepository<BookRequest, Integer> {

	@Query(value = "select * from book_request bookReq where bookReq.status= :statusCode and bookReq.book_id= :bookId order by requested_date asc limit 1", nativeQuery = true)
	Optional<BookRequest> findOneByStatusAndBookId(Integer statusCode, Integer bookId);
}
