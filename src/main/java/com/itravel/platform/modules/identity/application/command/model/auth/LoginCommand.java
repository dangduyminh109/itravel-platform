package com.itravel.platform.modules.identity.application.command.model.auth;

import com.itravel.platform.modules.identity.domain.account.RawPassword;

public record LoginCommand(String identifier, RawPassword password) {
}
