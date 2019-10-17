package com.hcl.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.lms.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	public List<Book> findByBookTitleOrAuthor(String bookTitle, String author);
}
