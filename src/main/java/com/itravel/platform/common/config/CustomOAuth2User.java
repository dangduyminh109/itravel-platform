package com.itravel.platform.common.config;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final Map<String, Object> attributes;
    @Getter
    private final Account account;

    public CustomOAuth2User(Map<String, Object> attributes,
                            Account account) {
        this.attributes = attributes;
        this.account = account;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().value()))
                .toList();
    }

    @Override
    public String getName() {
        return account.getId().value();
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}