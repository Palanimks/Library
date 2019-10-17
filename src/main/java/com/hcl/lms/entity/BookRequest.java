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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookRequestId;
	private Integer userId;
	private Integer bookId;
	private LocalDate requestedDate;
	private Integer status; //1 alloted 0 not alloted
}