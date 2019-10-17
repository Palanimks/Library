package com.hcl.lms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.lms.controller.BookController;
import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.service.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	private BookServiceImpl bookServiceImpl;
	
	@InjectMocks
	private BookController bookController;
	
	ResponseDto responseDto=null;
	BookDto bookDto = null;
	
	@Before
	public void setUp() {
		responseDto = new ResponseDto();
		responseDto.setMessage("Book has added");
		responseDto.setStatus("Success");
		
		bookDto = new BookDto();
		bookDto.setAuthor("Laxman");
		bookDto.setBookTitle("Java Beans");
		bookDto.setCategory("Java");
		bookDto.setIsbn("HCLJ100123");
		bookDto.setUserId(101);
	}
	
	@Test
	public void addBookTest() {
		Mockito.when(bookServiceImpl.addBook(bookDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> actualResponseDto = bookController.addBook(bookDto);
		assertEquals(201, actualResponseDto.getStatusCode().value());
	}
}
