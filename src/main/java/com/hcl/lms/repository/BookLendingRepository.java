package com.hcl.lms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.lms.entity.BookLending;

/**
 * @author Laxman
 * @date 16-Oct-2019
 * 
 */
public interface BookLendingRepository extends JpaRepository<BookLending, Integer> {

	Long countByUserIdAndStatus(Integer userId, Integer status);
	
	Optional<BookLending> findByBookIdAndStatus(Integer bookId, Integer status);
	
	List<BookLending> findByDueDate(LocalDate dueDate);
	
}