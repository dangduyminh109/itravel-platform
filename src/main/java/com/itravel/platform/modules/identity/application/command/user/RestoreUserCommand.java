package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;

public record RestoreUserCommand(UserId userId) {
}
