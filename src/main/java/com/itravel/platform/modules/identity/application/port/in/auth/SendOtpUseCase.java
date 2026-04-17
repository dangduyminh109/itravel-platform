package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.SendOtpCommand;
import jakarta.mail.MessagingException;

public interface SendOtpUseCase {
    String execute(SendOtpCommand command) throws MessagingException;
}
