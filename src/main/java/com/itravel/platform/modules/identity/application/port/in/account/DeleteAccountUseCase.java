package com.itravel.platform.modules.identity.application.port.in.account;

import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;

public interface DeleteAccountUseCase {
    void execute(DeleteAccountCommand command);
}
