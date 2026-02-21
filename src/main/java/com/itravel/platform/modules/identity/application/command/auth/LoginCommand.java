package com.itravel.platform.modules.identity.application.command.auth;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public record LoginCommand(String identifier, PasswordHash password) {
}
