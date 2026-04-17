package com.itravel.platform.modules.identity.application.port.in.account;

import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountPasswordCommand;

public interface ChangePasswordUseCase {
    void execute(UpdateAccountPasswordCommand command);
}
