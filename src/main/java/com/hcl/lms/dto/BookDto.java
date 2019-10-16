package com.hcl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDto {

	private Integer userId;
	private String isbn;
	private String bookTitle;
	private String category;
	private String author;
}
