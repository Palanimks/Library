package com.hcl.lms.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BookLending;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Mock
	BookRepository bookRepository;
	
	@Mock
	BookLendingRepository bookLendingRepository;
	
	List<Book> books = new ArrayList<>();
	
	Book book  = new Book();
	
	BookLending bookLending;
	
	@Before
	public void init()
	{
		book.setAuthor("abc");
		book.setBookId(101);
		book.setBookTitle("cs");
		book.setCategory("xyz");
		
		books.add(book);
		bookLending = new BookLending();
		bookLending.setBookId(101);
		bookLending.setStatus(1);
	}
	
	@Test
	public void searchBookByBookTitleOrAuthorTest()
	{
		Mockito.when(bookRepository.findByBookTitleOrAuthor(Mockito.any(), Mockito.any())).thenReturn(books);
		
		Mockito.when(bookLendingRepository.findByBookId(Mockito.anyInt())).thenReturn(Optional.of(bookLending));
		
		List<SearchBookResponseDto> responses = bookServiceImpl.searchBookByBookTitleOrAuthor("cs", "abc");
		
		assertEquals(101, responses.get(0).getBookId());
	}

}
