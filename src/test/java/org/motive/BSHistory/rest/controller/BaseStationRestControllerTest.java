package org.motive.BSHistory.rest.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.rest.controller.BaseStationRestController;
import org.motive.BSHistory.service.IBSService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BaseStationRestControllerTest {

	private BaseStation filledBS;
	private List<BaseStation> baseStationList = new ArrayList<BaseStation>();

	@InjectMocks
	private BaseStationRestController controller;

	@Mock
	private IBSService service;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		filledBS = new BaseStation();
		filledBS.setId(123L);
		filledBS.setVersion(3);
		filledBS.setTitle("bsTestTitle");
		filledBS.setCreationDate(new Date());
		filledBS.setDescription("description for test");

		baseStationList.add(filledBS);
		baseStationList.add(filledBS);

		// create mock for mvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// create mocks for service
		when(service.findById(123L)).thenReturn(filledBS);
		when(service.findById(1L)).thenReturn(null);
		when(service.findAll()).thenReturn(baseStationList);
	}
	

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/rs/bsList"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].title").value(filledBS.getTitle()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[0].id").value(
								isA(Number.class)))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[0].id").value(
								anyOf(equalTo((Number) filledBS.getId()),
										equalTo((Number) filledBS.getId()
												.intValue()))))
				.andExpect(
						jsonPath("$.[0].description").value(filledBS.getDescription()))
				.andExpect(jsonPath("$.[0].application", isEmptyOrNullString()))
				.andExpect(
						jsonPath("$.[0].creationDate").value(filledBS.getCreationDateString()))

				.andExpect(jsonPath("$.[1].title").value(filledBS.getTitle()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[1].id").value(
								isA(Number.class)))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[1].id").value(
								anyOf(equalTo((Number) filledBS.getId()),
										equalTo((Number) filledBS.getId()
												.intValue()))))
				.andExpect(
						jsonPath("$.[1].description").value(filledBS.getDescription()))
				.andExpect(jsonPath("$.[1].application", isEmptyOrNullString()))
				.andExpect(
						jsonPath("$.[1].creationDate").value(filledBS.getCreationDateString()));
	}
	
	@Test
	public void testCreateFormJSON() throws Exception {

		mockMvc.perform(
				post("/rs/createBS")
						.content(
								"{\"id\":123,\"version\":3,\"title\":\"bsTestTitle\", \"description\":\"description for test\",\"creationDate\":\"06.09.2015\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void getExistingBaseStationTest() throws Exception {
		mockMvc.perform(get("/rs/baseStation/"+filledBS.getId()))
		.andExpect(jsonPath("$.title").value(filledBS.getTitle()))
		.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/baseStation/123"))))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getNonExistingBaseStationTest() throws Exception {
		mockMvc.perform(get("/rs/baseStation/1"))
		.andExpect(status().isNotFound());
	}
	
}
