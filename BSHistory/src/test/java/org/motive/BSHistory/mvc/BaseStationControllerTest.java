package org.motive.BSHistory.mvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.service.IBSService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;


public class BaseStationControllerTest {

	private BaseStation filledBS;
	
	@InjectMocks
	private BaseStationController controller;
	
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
		
		//create mock for mvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		//create mocks for service
		when(service.findByIdAndTitle(123L, "bsTestTitle")).thenReturn(filledBS);
		when(service.findById(123L)).thenReturn(filledBS);
		when(service.findByTitle("bsTestTitle")).thenReturn(filledBS);
	}
	
	@Test
	public void testMVC() throws Exception {
		mockMvc.perform(get("/common/hello?id=123")).andDo(print());
	}
	
	@Test
	public void testReturnValue() {
		assertEquals("BaseStationController test returned value", "home", controller.printWelcome(new ExtendedModelMap(), null, null));
	}
}
