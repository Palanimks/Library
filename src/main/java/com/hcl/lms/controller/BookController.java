package com.hcl.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Laxman
 * @date 16-Oct-2019
 * 
 *       This is BookControoler to perform operations on Book
 */
@Slf4j
@RestController
@RequestMapping("/lms")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * @author Laxman
	 * @param bookDto
	 * @return responseDto
	 * 
	 *         In this method passing BookDto to add new Book in library in response
	 *         resnding ResponseDto with status code 201 on success addition to
	 *         database
	 */
	@PostMapping("/books")
	public ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookDto) {
		
		log.info(" :: addBook() ----- ");
		return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
	}
}
