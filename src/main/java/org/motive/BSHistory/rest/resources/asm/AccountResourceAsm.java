package org.motive.BSHistory.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.motive.BSHistory.core.models.entities.Account;
import org.motive.BSHistory.rest.controllers.AccountRestController;
import org.motive.BSHistory.rest.resources.AccountResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
	
	public AccountResourceAsm() {
		super(AccountRestController.class, AccountResource.class);

	}

	@Override
	public AccountResource toResource(Account account) { 
		AccountResource res = new AccountResource();
		res.setName(account.getName());
		res.setPassword(account.getPassword());
		Link link = linkTo(methodOn(AccountRestController.class).getAccount(account.getId())).withSelfRel(); 
		res.add(link.withSelfRel());
		return res;
	}
}
