package com.itravel.platform.modules.identity.application.port.in.account;

import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;

public interface DestroyAccountUseCase {
    void execute(DeleteAccountCommand command);
}
