package com.hcl.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.entity.BookLending;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author Sushil 
 *
 */
@Slf4j
@Service 
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookLendingRepository bookLendingRepository;

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

}
