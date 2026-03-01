package com.itravel.platform.common.config;

import com.itravel.platform.modules.identity.application.query.AuthToken;
import com.itravel.platform.modules.identity.application.service.TokenApplicationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler
        implements AuthenticationSuccessHandler {

    private final TokenApplicationService tokenApplicationService;

    @Value("${security.jwt.valid-duration}")
    private int validDuration;

    @Value("${security.refresh-token.refreshable-duration}")
    private int refreshDuration;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {

        if (!(authentication.getPrincipal()
                instanceof CustomOAuth2User principal)) {
            throw new IllegalStateException("Invalid principal");
        }

        AuthToken token;
        try {
            token = tokenApplicationService
                    .createToken(principal.getAccount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean secure = request.isSecure();

        Cookie refreshCookie =
                new Cookie("refresh", token.refreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(secure);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(refreshDuration);
        response.addCookie(refreshCookie);

        Cookie accessCookie =
                new Cookie("access", token.accessToken());
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(secure);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(validDuration);
        response.addCookie(accessCookie);

        response.sendRedirect("http://localhost:3000/api/auth/callback");
    }
}
