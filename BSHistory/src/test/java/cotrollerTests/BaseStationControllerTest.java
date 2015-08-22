package cotrollerTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.mvc.BaseStationController;
import org.motive.BSHistory.service.IBSService;
import org.springframework.ui.ExtendedModelMap;

public class BaseStationControllerTest {

	private BaseStation filledBS;
	
	@Mock
	private IBSService bsService;
	
	@InjectMocks
	BaseStationController bsController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		filledBS = new BaseStation();
		filledBS.setId(123L);
		filledBS.setVersion(3);
		filledBS.setTitle("bsTestTitle");
		filledBS.setCreationDate(new Date());
		filledBS.setDescription("description for test");
	}
	
	@Test
	public void testBaseStationController() {
		when(bsService.findById(123L)).thenReturn(filledBS);
		when(bsService.findByTitle("bsTestTitle")).thenReturn(filledBS);
		
		assertEquals("mock test findById", "home", bsController.printWelcome(new ExtendedModelMap(), null, null));
	}
}
