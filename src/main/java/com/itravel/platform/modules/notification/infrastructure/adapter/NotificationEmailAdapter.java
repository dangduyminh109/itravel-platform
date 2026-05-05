package com.itravel.platform.modules.notification.infrastructure.adapter;

import com.itravel.platform.modules.notification.application.port.out.notification.NotificationEmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationEmailAdapter implements NotificationEmailPort {
    JavaMailSender mailSender;

    @NonFinal
    @Value("${notification.mail.from}")
    String fromEmail;

    @Override
    public void sendNotificationEmail(String to, String recipientName, String title, String content) {
        try {
            String htmlContent = buildEmailContent(recipientName, content);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(title + " - Travel Platform");
            helper.setText(htmlContent, true);
            helper.setFrom(fromEmail);

            mailSender.send(message);
            log.info("Notification email sent successfully to: {}", to);

        } catch (MessagingException e) {
            log.error("Failed to send notification email to: {}", to, e);
        } catch (Exception e) {
            log.error("Error occurred while processing notification email for: {}", to, e);
        }
    }

    private String buildEmailContent(String userName, String content) {
        return String.format("""
                <div style="font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2">
                    <div style="margin:50px auto;width:70%%;padding:20px 0">
                        <div style="border-bottom:1px solid #eee">
                            <a href="" style="font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600">Travel Platform</a>
                        </div>
                        <p style="font-size:1.1em">Xin chào %s,</p>
                        <p>Bạn có một thông báo mới từ Travel Platform:</p>
                        <div style="background: #f9f9f9; padding: 15px; border-left: 4px solid #00466a; margin: 20px 0;">
                            %s
                        </div>
                        <p style="font-size:0.9em;">Trân trọng,<br />Travel Platform Team</p>
                    </div>
                </div>
                """, userName, content);
    }
}
