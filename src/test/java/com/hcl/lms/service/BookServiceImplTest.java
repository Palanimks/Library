package com.hcl.lms.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Mock
	BookRepository bookRepository;
	
	List<Book> books = new ArrayList<>();
	
	Book book  = new Book();
	
	@Before
	public void init()
	{
		book.setAuthor("abc");
		book.setBookId(101);
		book.setBookTitle("cs");
		book.setCategory("xyz");
		
		books.add(book);
	}
	
	@Test
	public void searchBookByBookTitleOrAuthorTest()
	{
		Mockito.when(bookRepository.findByBookTitleOrAuthor(Mockito.any(), Mockito.any())).thenReturn(books);
		
		List<SearchBookResponseDto> responses = bookServiceImpl.searchBookByBookTitleOrAuthor("cs", "abc");
		
		assertEquals(101, responses.get(0).getBookId());
	}

}
