package org.motive.BSHistory.rest.controller;

import static org.hamcrest.Matchers.anyOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.motive.BSHistory.core.models.entities.BaseStation;
import org.motive.BSHistory.core.services.IBSService;
import org.motive.BSHistory.rest.controllers.BaseStationRestController;
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
	}

	@Test
	public void getListTest() throws Exception {

		when(service.findAllBaseStations()).thenReturn(baseStationList);

		mockMvc.perform(get("/rs/baseStation/list"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].title").value(filledBS.getTitle()))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[0].id").value(isA(Number.class)))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.[0].id").value(
								anyOf(equalTo((Number) filledBS.getId()),
										equalTo((Number) filledBS.getId()
												.intValue()))))
				.andExpect(
						jsonPath("$.[0].description").value(
								filledBS.getDescription()))
				.andExpect(jsonPath("$.[0].application", isEmptyOrNullString()))
				.andExpect(jsonPath("$.[0].creationDate").value(filledBS.getCreationDateString()))

				.andExpect(jsonPath("$.[1].title").value(filledBS.getTitle()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(isA(Number.class)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(
								anyOf(equalTo((Number) filledBS.getId()),
										equalTo((Number) filledBS.getId().intValue()))))
				.andExpect(jsonPath("$.[1].description").value(filledBS.getDescription()))
				.andExpect(jsonPath("$.[1].application", isEmptyOrNullString()))
				.andExpect(jsonPath("$.[1].creationDate").value(filledBS.getCreationDateString()));
	}

	@Test
	public void createBaseStationTest() throws Exception {

		when(service.create(any(BaseStation.class))).thenReturn(filledBS);

		mockMvc.perform(
				post("/rs/baseStation")
						.content(
								"{\"id\":123,\"version\":3,\"title\":\"bsTestTitle\", "
								+ "\"description\":\"description for test\", "
								+ "\"creationDate\":\"06.09.2015\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title").value(filledBS.getTitle()))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/baseStation/123"))))
				.andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void getBaseStationTest() throws Exception {

		when(service.find(eq(123L))).thenReturn(filledBS);

		mockMvc.perform(get("/rs/baseStation/123"))
				.andExpect(jsonPath("$.title").value(filledBS.getTitle()))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/baseStation/123"))))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateBaseStationTest() throws Exception {

		when(service.update(eq(123L), any(BaseStation.class))).thenReturn(filledBS);

		mockMvc.perform(put("/rs/baseStation/123")
				.content(
						"{\"id\":123,\"version\":3,\"title\":\"bsTestTitle\", "
						+ "\"description\":\"description for test\", "
						+ "\"creationDate\":\"06.09.2015\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getNonExistingBaseStationTest() throws Exception {

		when(service.find(eq(1L))).thenReturn(null);

		mockMvc.perform(get("/rs/baseStation/1")).andExpect(
				status().isNotFound());
	}

}
