package com.itravel.platform.modules.identity.application.command.auth;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RawPassword;

public record LoginCommand(String identifier, RawPassword password) {
}
