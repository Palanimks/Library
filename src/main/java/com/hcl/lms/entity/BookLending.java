package com.hcl.lms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Laxman
 * @date 16-Oct-2019
 * 
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLending {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookLendingId;
	private Integer userId;
	private Integer bookId;
	private LocalDate issuedDate;
	private LocalDate dueDate;
	private Integer status; //1 returned, 0 taken
	private String remarks;
}
