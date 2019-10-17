package com.hcl.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.lms.dto.BookStatusResponseDto;
import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Sushil This controller is use to search book
 * @ date 
 *
 */

@Slf4j
@RequestMapping("/lms")
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class BookSearchController {

	@Autowired
	BookService BookService;
	/**
	 * This method is use to search book based on book title or author
	 * @param bookTitle
	 * @param author
	 * @return List<SearchBookResponseDto>
	 */
	@GetMapping("/books")
	public ResponseEntity<List<SearchBookResponseDto>> searchBookByTitleOrAuthor(@RequestParam(required = false) String bookTitle, @RequestParam(required = false) String author)
	{
		log.info("Inside searchBookByTitleOrAuthor of BookSearchController");
		List<SearchBookResponseDto> response = BookService.searchBookByBookTitleOrAuthor(bookTitle, author);
		
		return new ResponseEntity<List<SearchBookResponseDto>>(response, HttpStatus.OK);
	}
	   /**
     * This method is use to get status of book by book id
     * @param bookId, not null
     * @return BookStatusResponseDto
     */
	@GetMapping("/books/{bookId}")
	public ResponseEntity<BookStatusResponseDto> getBookStatusByBookId(@PathVariable int bookId)
	{
		log.info("Inside getBookStatusByBookId of BookSearchController");
		BookStatusResponseDto response = BookService.getBookStatusByBookId(bookId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
