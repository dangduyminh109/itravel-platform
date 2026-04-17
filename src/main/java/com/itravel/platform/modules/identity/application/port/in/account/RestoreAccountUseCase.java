package com.itravel.platform.modules.identity.application.port.in.account;

import com.itravel.platform.modules.identity.application.command.model.account.RestoreAccountCommand;

public interface RestoreAccountUseCase {
    void execute(RestoreAccountCommand command);
}
