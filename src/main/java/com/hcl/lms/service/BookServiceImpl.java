package com.hcl.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.BookStatusResponseDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BookLending;
import com.hcl.lms.entity.BookRequest;
import com.hcl.lms.exception.LimitExceededException;
import com.hcl.lms.exception.NoBookAvailableException;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.repository.BookRequestRepository;
import com.hcl.lms.utility.LibraryUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Laxman/ sushil
 * @date 16-Oct-2019
 */
@Slf4j
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
			responseDto.setStatus("Success");
		}
		return responseDto;
	}
	
	/**
     * This method is use to search book based on book title or author
     * @param bookTitle 
     * @param author 
     * @return List<SearchBookResponseDto> 
     */
	@Override
	public List<SearchBookResponseDto> searchBookByBookTitleOrAuthor(String bookTitle, String author) {
		log.info("Inside searchBookByBookTitleOrAuthor of BookServiceImpl");
		List<SearchBookResponseDto> responseDto= new ArrayList<>();
		List<Book> books =null;
		
		books = bookRepository.findByBookTitleOrAuthor(bookTitle, author);
		
		if (!books.isEmpty()) {
			books.stream().forEach(myBook -> {
				SearchBookResponseDto bookDto = new SearchBookResponseDto();
				Optional<BookLending> booklanding = bookLendingRepository.findByBookId(myBook.getBookId());
				BeanUtils.copyProperties(myBook, bookDto);
				bookDto.setStatus(booklanding.get().getStatus());
				responseDto.add(bookDto);
			});
		}
		return responseDto;
	}
    /**
     * This method is use to get status of book by book id
     * @param bookId, not null
     * @return BookStatusResponseDto
     * @exception throws NoBookAvailableException if book id does not exist 
     */
	@Override
	public BookStatusResponseDto getBookStatusByBookId(int bookId) {
		BookStatusResponseDto responseDto;
		Optional<BookLending> booklanding = bookLendingRepository.findByBookId(bookId);
		if(booklanding.isPresent())
		{
		    responseDto = new BookStatusResponseDto();
			BeanUtils.copyProperties(booklanding.get(), responseDto);
		}
		else
		{
			throw new NoBookAvailableException(LibraryUtility.NO_BOOK_AVAILABLE_EXCEPTION);
		}
		return responseDto;
	}
}
