package com.itravel.platform.modules.identity.application.command.model.user;

import com.itravel.platform.modules.identity.domain.user.UserId;

public record RestoreUserCommand(UserId id) {
}
