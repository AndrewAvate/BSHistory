package org.motive.BSHistory.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.motive.BSHistory.core.models.entities.Account;

public class AccountTest {
	private Account filledAccount;
	private Account emtptyAccount;

	@Before
	public void setup() {
		emtptyAccount = new Account();
		
		filledAccount = new Account();
		filledAccount.setId(1L);
		filledAccount.setVersion(2);
		filledAccount.setName("testName");
		filledAccount.setPassword("testPassword");
	}
	
	@Test
	public void testEmptyAccount() {
		assertEquals("account \"id\" on init", null, emtptyAccount.getId());
		assertEquals("account \"version\" on init", 0, emtptyAccount.getVersion());
		assertEquals("account \"name\" on init", null, emtptyAccount.getName());
		assertEquals("account \"password\" on init", null, emtptyAccount.getPassword());

	}
	
	@Test
	public void testFilledAccount() {
		assertEquals("account \"version\"", Long.valueOf(1L), filledAccount.getId());
		assertEquals("account \"version\"", 2, filledAccount.getVersion());
		assertEquals("account \"name\"", "testName", filledAccount.getName());
		assertEquals("account \"password\"", "testPassword", filledAccount.getPassword());

	}
}
