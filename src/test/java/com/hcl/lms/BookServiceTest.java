package com.hcl.lms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.repository.BookRepository;
import com.hcl.lms.service.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	private BookRepository bookRepository;
	
	BookDto bookDto = null;
	
	@Before
	public void setUp() {		
		bookDto = new BookDto();
		bookDto.setAuthor("Laxman");
		bookDto.setBookTitle("Java Beans");
		bookDto.setCategory("Java");
		bookDto.setIsbn("HCLJ100123");
		bookDto.setUserId(101);
	}
	
	@Test
	public void addBookTest() {
		Mockito.when(bookRepository.save(Mockito.anyObject())).thenReturn(Mockito.anyObject());
		ResponseDto actualResult = bookServiceImpl.addBook(bookDto);
		assertEquals("Success", actualResult.getStatus());
	}
}
