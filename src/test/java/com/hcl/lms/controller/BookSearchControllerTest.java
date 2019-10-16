package com.hcl.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.lms.dto.SearchBookResponseDto;
import com.hcl.lms.service.BookService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BookSearchControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	BookSearchController bookSearchController;
	
	@Mock
	BookService bookService;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookSearchController).build();
    }
	
	public List<SearchBookResponseDto> getBooks()
	{
		List<SearchBookResponseDto> responses = new ArrayList<>();
		
		SearchBookResponseDto bookResponse = new SearchBookResponseDto();
		
		responses.add(bookResponse);
		return responses;
	}
	
	@Test
	public void getBalanceController()
	{
		ResponseEntity<List<SearchBookResponseDto>> expResult = new ResponseEntity<List<SearchBookResponseDto>>(getBooks(), HttpStatus.OK);
		/*when(leaveService.getMyLeaves(Mockito.anyInt())).thenReturn(getLeaves());
		ResponseEntity<MyLeavesDto> actResult = leaveController.getMyLeavesAvailable(Mockito.anyInt());
		assertEquals(expResult.getStatusCode(), actResult.getStatusCode());*/
	}

}
