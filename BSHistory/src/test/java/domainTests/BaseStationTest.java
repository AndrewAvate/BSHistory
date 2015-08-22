package domainTests;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.motive.BSHistory.domain.BaseStation;

public class BaseStationTest {

	private BaseStation filledBS;
	private BaseStation emtptyBS;
	private byte[] applicationToBS;
	private Date creationDateToBS;

	@Before
	public void setup() {
		emtptyBS = new BaseStation();

		filledBS = new BaseStation();
		filledBS.setId(123L);
		filledBS.setVersion(3);
		filledBS.setTitle("bsTestTitle");
		applicationToBS = new byte[] { 1, 2, 3, 4 };
		filledBS.setApplication(applicationToBS);
		creationDateToBS = new Date(1);
		filledBS.setCreationDate(creationDateToBS);
		filledBS.setDescription("description for test");
		;

	}

	@Test
	public void testEmptyBaseStation() {
		assertEquals("base station \"id\" on init", null, emtptyBS.getId());
		assertEquals("base station \"title\" on init", 0, emtptyBS.getVersion());
		assertEquals("base station \"title\" on init", null,
				emtptyBS.getTitle());
		assertEquals("base station \"application\" on init", null,
				emtptyBS.getApplication());
		assertEquals("base station \"creation date\" on init", null,
				emtptyBS.getCreationDate());
		assertEquals("base station \"description\" on init", null,
				emtptyBS.getDescription());
		assertEquals("filled base station \"formatted creation date\"", "",
				emtptyBS.getCreateDateString());

	}

	@Test
	public void testFilledBaseStation() {
		assertEquals("filled base station \"id\"", Long.valueOf(123L),
				filledBS.getId());
		assertEquals("filled base station \"title\"", 3, filledBS.getVersion());
		assertEquals("filled base station \"title\"", "bsTestTitle",
				filledBS.getTitle());
		assertEquals("filled base station \"application\"", applicationToBS,
				filledBS.getApplication());
		assertEquals("filled base station \"creation date\"", creationDateToBS,
				filledBS.getCreationDate());
		assertEquals("filled base station \"description\"",
				"description for test", filledBS.getDescription());
		assertEquals("filled base station \"formatted creation date\"",
				"01.01.1970", filledBS.getCreateDateString());
		assertEquals(
				"filled base station \"toString()\"",
				"Base Station - Id: 123, title: bsTestTitle, creation date: 01.01.1970, Description: description for test",
				filledBS.toString());

	}
}
