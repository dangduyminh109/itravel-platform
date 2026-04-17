package com.itravel.platform.modules.identity.application.port.out.email;

import jakarta.mail.MessagingException;

public interface EmailSenderPort {
    void sendEmail(String to, String otp) throws MessagingException;
}
