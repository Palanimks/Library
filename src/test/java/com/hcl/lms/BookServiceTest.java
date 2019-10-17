package com.hcl.lms;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.entity.BookLending;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.service.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private BookLendingRepository bookLendingRepository;
	
	BookDto bookDto = null;
	Optional<BookLending> optBookLending =null;
	
	@Before
	public void setUp() {		
		bookDto = new BookDto();
		bookDto.setAuthor("Laxman");
		bookDto.setBookTitle("Java Beans");
		bookDto.setCategory("Java");
		bookDto.setIsbn("HCLJ100123");
		bookDto.setUserId(101);
		
		BookLending bookLending = new BookLending();
		optBookLending = Optional.empty();
	}
	
	@Test
	public void addBookTest() {
		Mockito.when(bookRepository.save(Mockito.anyObject())).thenReturn(Mockito.anyObject());
		ResponseDto actualResult = bookServiceImpl.addBook(bookDto);
		assertEquals("Success", actualResult.getStatus());
	}
	
	@Test
	public void borrowBookTest() {
		Mockito.when(bookLendingRepository.countByUserIdAndStatus(101, 0)).thenReturn(0L);
		Mockito.when(bookLendingRepository.findByBookIdAndStatus(1010, 0)).thenReturn(optBookLending);
		ResponseDto actualResponse = bookServiceImpl.borrowBook(1010, 101);
		assertEquals("Success", actualResponse.getStatus());
	}
}
