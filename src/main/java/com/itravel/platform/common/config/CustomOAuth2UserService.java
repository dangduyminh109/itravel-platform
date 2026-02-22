package com.itravel.platform.common.config;

import com.itravel.platform.modules.identity.application.command.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.application.handler.AuthCommandHandler;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    AuthCommandHandler authCommandHandler;
    AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        Account account = accountRepository.findByEmail(new Email(email))
                .orElseGet(() -> {
                    RegisterCustomerByGoogleCommand command = new RegisterCustomerByGoogleCommand(
                            new Email(email),
                            new FullName(name)
                    );
                    return authCommandHandler.RegisterCustomerByGoogle(command);
                });

        return new CustomOAuth2User(oAuth2User.getAttributes(),account);
    }
}
