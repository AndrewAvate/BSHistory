package org.motive.BSHistory.rest.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.motive.BSHistory.core.models.entities.Account;


public class AccountResourceTest {

	private AccountResource filledAccountResource;
	private AccountResource emtptyAccountResource;
	private Account account;
	
	@Before
	public void setup () {
		filledAccountResource = new  AccountResource();
		emtptyAccountResource = new AccountResource();
		
		account = new Account();
		account.setName("testName");
		account.setPassword("testPassword");		
		
		filledAccountResource.setName(account.getName());
		filledAccountResource.setPassword(account.getPassword());
	}
	
	@Test
	public void testEmptyAccountResource() {
		assertEquals("emtptyAccountResource \"name\" on init", null, emtptyAccountResource.getName());
		assertEquals("emtptyAccountResource \"password\" on init", null, emtptyAccountResource.getPassword());
		assertEquals("emtptyAccountResource \"toAccountName\" on init", null, emtptyAccountResource.toAccount().getName());
		assertEquals("emtptyAccountResource \"toAccountPassword\" on init", null, emtptyAccountResource.toAccount().getPassword());
		assertEquals("emtptyAccountResource \"toAccountPassword\" on init", 0, emtptyAccountResource.toAccount().getVersion());

	}
	
	@Test
	public void testFilledAccountResource() {
		assertEquals("filledAccountResource \"name\"", "testName", filledAccountResource.getName());
		assertEquals("filledAccountResource \"password\"", "testPassword", filledAccountResource.getPassword());
		assertEquals("filledAccountResource \"toAccountName\"", "testName", filledAccountResource.toAccount().getName());
		assertEquals("filledAccountResource \"toAccountPassword\"", "testPassword", filledAccountResource.toAccount().getPassword());
		assertEquals("filledAccountResource \"toAccountPassword\"", 0, filledAccountResource.toAccount().getVersion());
	}
	
}
