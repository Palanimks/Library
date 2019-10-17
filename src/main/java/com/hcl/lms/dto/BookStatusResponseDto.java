package com.hcl.lms.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author User1
 * 
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class BookStatusResponseDto {
	
	private Integer bookId;
	private LocalDate dueDate;
	private Integer userId;
	private Integer status;
}
