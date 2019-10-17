package com.hcl.lms.service;

import java.util.List;

import com.hcl.lms.dto.BookDto;
import com.hcl.lms.dto.BookStatusResponseDto;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.SearchBookResponseDto;

public interface BookService {

	ResponseDto addBook(BookDto bookDto);

	ResponseDto borrowBook(Integer bookId, Integer userId);

	public List<SearchBookResponseDto> searchBookByBookTitleOrAuthor(String bookTitle, String author);

	public BookStatusResponseDto getBookStatusByBookId(int bookId);
}
