package com.hcl.lms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.repository.BookRepository;

/**
 * @author Laxman
 * @date 16-Oct-2019
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Value("${book.add.message.success}")
	private String bookAddMessageSuccess;

	@Value("${book.status}")
	private String bookStatus;

	/**
	 * @author Laxman
	 * @param bookDto
	 * @return responseDto
	 * 
	 *         This method is to add new Book in library.
	 *         In this method passing BookDto, creating object of Book Entity copying
	 *         to Book entity and saving to database
	 */
	@Override
	public ResponseDto addBook(BookDto bookDto) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		bookRepository.save(book);
		return ResponseDto.builder().message(bookAddMessageSuccess).status(bookStatus).build();
	}

}
