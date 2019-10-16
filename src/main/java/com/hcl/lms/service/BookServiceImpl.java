package com.hcl.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.entity.Book;
import com.hcl.lms.repository.BookRepository;
/**
 * 
 * @author Sushil 
 *
 */
@Service 
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

    /**
     * This method is use to search book based on book title or author
     * @param bookTitle 
     * @param author 
     * @return List<SearchBookResponseDto> 
     */
	@Override
	public List<SearchBookResponseDto> searchBookByBookTitleOrAuthor(String bookTitle, String author) {
		
		List<SearchBookResponseDto> responseDto= new ArrayList<>();
		List<Book> books =null;
		
		books = bookRepository.findByBookTitleOrAuthor(bookTitle, author);
		
		if (!books.isEmpty()) {
			books.stream().forEach(myBook -> {
				SearchBookResponseDto bookDto = new SearchBookResponseDto();
				BeanUtils.copyProperties(myBook, bookDto);
				responseDto.add(bookDto);
			});
		}
		
		return responseDto;
	}

}
