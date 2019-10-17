package com.hcl.lms.scheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcl.lms.entity.BookLending;
import com.hcl.lms.entity.BookRequest;
import com.hcl.lms.repository.BookLendingRepository;
import com.hcl.lms.repository.BookRequestRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LmsUpdateScheduler {

	@Autowired
	private BookLendingRepository bookLendingRepository;

	@Autowired
	private BookRequestRepository bookRequestRepository;
	

	@Value("${book.request.statusCode}")
	private Integer statusCode;
	

	@Value("${book.lend.days}")
	private Integer lenddays;

	@Scheduled(cron = "0 */1 * * * *")
	public void updateBookLending() {
		log.info(" :: updateBookLending ---");
		List<BookLending> bookLendings = bookLendingRepository.findByDueDate(LocalDate.now());
		List<BookLending> updtebookLendings = new ArrayList<>();
		log.info(" :: updateBookLending --- {}",bookLendings.size());
		bookLendings.stream().forEach(bookLending -> {
			bookLending.setStatus(1);
			Optional<BookRequest> optBookRequest = bookRequestRepository.findOneByStatusAndBookId(statusCode,
					bookLending.getBookId());
			if (optBookRequest.isPresent()) {
				optBookRequest.get().setStatus(1);
				bookRequestRepository.save(optBookRequest.get());
				BookLending newbookLending = BookLending.builder().userId(optBookRequest.get().getUserId())
						.bookId(optBookRequest.get().getBookId()).issuedDate(LocalDate.now()).status(0).remarks("")
						.dueDate(LocalDate.now().plusDays(lenddays)).build();
				bookLendingRepository.save(newbookLending);
			}
			updtebookLendings.add(bookLending);
		});
		bookLendingRepository.saveAll(updtebookLendings);
	}
}
