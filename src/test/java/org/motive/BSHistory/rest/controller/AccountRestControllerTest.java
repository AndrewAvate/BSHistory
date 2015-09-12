package org.motive.BSHistory.rest.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.motive.BSHistory.core.models.entities.Account;
import org.motive.BSHistory.core.models.entities.BaseStation;
import org.motive.BSHistory.core.services.IAccountService;
import org.motive.BSHistory.rest.controllers.AccountRestController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AccountRestControllerTest {
	
	private Account filledAccount;
	
	@InjectMocks
	private AccountRestController controller;

	@Mock
	private IAccountService service;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		filledAccount = new Account();
		filledAccount.setId(23L);
		filledAccount.setVersion(3);
		filledAccount.setName("testName");
		filledAccount.setPassword("testPassword");

		// create mock for mvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void getAccountTest() throws Exception {

		when(service.findAccount(eq(23L))).thenReturn(filledAccount);

		mockMvc.perform(get("/rs/account/23"))
				.andExpect(jsonPath("$.name").value(filledAccount.getName()))
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/account/23"))))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void createAccountTest() throws Exception {

		when(service.createAccount(any(Account.class))).thenReturn(filledAccount);

		mockMvc.perform(
				post("/rs/account")
						.content("{\"name\":\"testName\", \"password\":\"testPassword\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value(filledAccount.getName()))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/account/23"))))
				.andExpect(status().isCreated()).andDo(print());
	}
}
