package org.motive.BSHistory.rest.resources;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BaseStationResourceTest {

	private BaseStationResource filledBSResource;
	private BaseStationResource emtptyBSResource;
	private byte[] application;
	private Date creationDate;
	
	@Before
	public void setup() {
		emtptyBSResource = new BaseStationResource();

		application = new byte[] { 1, 2, 3, 4 };
		creationDate = new Date(1);
		filledBSResource = new BaseStationResource();
		filledBSResource.setApplication(application);
		filledBSResource.setCreationDate(creationDate);
		filledBSResource.setDescription("testDescription");
		filledBSResource.setTitle("testTitle");
	}
	
	@Test
	public void testEmptyBSResource() {
		assertEquals("emtptyBSResource \"application\" on init", null, emtptyBSResource.getApplication());
		assertEquals("emtptyBSResource \"CreationDate\" on init", null, emtptyBSResource.getCreationDate());
		assertEquals("emtptyBSResource \"Description\" on init", null, emtptyBSResource.getDescription());
		assertEquals("emtptyBSResource \"Title\" on init", null, emtptyBSResource.getTitle());
	}
	
	@Test
	public void testFilledBSResource() {
		assertEquals("filledBSResource \"application\"", application, filledBSResource.getApplication());
		assertEquals("filledBSResource \"CreationDate\"", creationDate, filledBSResource.getCreationDate());
		assertEquals("filledBSResource \"Description\"", "testDescription", filledBSResource.getDescription());
		assertEquals("filledBSResource \"Title\"", "testTitle", filledBSResource.getTitle());
	}
	
}
