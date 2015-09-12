package org.motive.BSHistory.core.services;

import org.motive.BSHistory.core.models.entities.Account;

public interface IAccountService {
	
    Account findAccount(Long id);
    Account createAccount(Account data);
}
