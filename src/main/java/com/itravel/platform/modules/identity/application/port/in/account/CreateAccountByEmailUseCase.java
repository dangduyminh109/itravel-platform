package com.itravel.platform.modules.identity.application.port.in.account;
import com.itravel.platform.modules.identity.application.command.model.account.*;
import com.itravel.platform.modules.identity.domain.account.Account;

public interface CreateAccountByEmailUseCase { Account execute(CreateAccountByEmailCommand command); }
