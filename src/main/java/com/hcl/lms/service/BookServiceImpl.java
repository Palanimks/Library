package com.hcl.lms.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BookLending;
import com.hcl.lms.entity.BookRequest;
import com.hcl.lms.exception.LimitExceededException;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BookRequestRepository;

/**
 * @author Laxman
 * @date 16-Oct-2019
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookLendingRepository bookLendingRepository;

	@Autowired
	private BookRequestRepository bookRequestRepository;

	@Value("${book.add.message.success}")
	private String bookAddMessageSuccess;

	@Value("${book.status}")
	private String bookStatus;

	@Value("${book.max.allowed}")
	private String bookMaxAllowed;
	
	@Value("${book.request}")
	private String forBookRequest;
	
	@Value("${book.request.book.alloted}")
	private String bookRequestBookAlloted;
	/**
	 * @author Laxman
	 * @param bookDto
	 * @return responseDto
	 * 
	 *         This method is to add new Book in library. In this method passing
	 *         BookDto, creating object of Book Entity copying to Book entity and
	 *         saving to database
	 */
	@Override
	public ResponseDto addBook(BookDto bookDto) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		bookRepository.save(book);
		return ResponseDto.builder().message(bookAddMessageSuccess).status(bookStatus).build();
	}

	@Override
	public ResponseDto borrowBook(Integer bookId, Integer userId) {

		ResponseDto responseDto = new ResponseDto();
		Long count = bookLendingRepository.countByUserIdAndStatus(userId, 0);
		if (count >= 3) {
			throw new LimitExceededException(bookMaxAllowed);
		}
		Optional<BookLending> optBookLending = bookLendingRepository.findByBookIdAndStatus(bookId, 0);
		if (optBookLending.isPresent()) {
			BookRequest bookRequest = BookRequest.builder().bookId(bookId).userId(userId).requestedDate(LocalDate.now())
					.status(0).build();
			bookRequestRepository.save(bookRequest);
			responseDto.setMessage(forBookRequest);
			responseDto.setStatus(bookStatus);
		} else {
			BookLending bookLending = BookLending.builder().userId(userId).bookId(bookId).issuedDate(LocalDate.now())
					.status(0).remarks("").dueDate(LocalDate.now().plusDays(14)).build();
			bookLendingRepository.save(bookLending);
			responseDto.setMessage(bookRequestBookAlloted);
			responseDto.setStatus(bookStatus);
		}
		return responseDto;
	}

}
