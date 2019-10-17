package com.hcl.lms.service;

import java.util.List;

import com.hcl.lms.dto.BookStatusResponseDto;
import com.hcl.lms.dto.SearchBookResponseDto;

public interface BookService {
	
	public List<SearchBookResponseDto> searchBookByBookTitleOrAuthor(String bookTitle,String author);
	
	public BookStatusResponseDto getBookStatusByBookId(int bookId);

}
