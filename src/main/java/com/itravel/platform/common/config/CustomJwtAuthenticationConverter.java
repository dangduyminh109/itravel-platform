package com.itravel.platform.common.config;

import com.itravel.platform.common.exceptions.UnAuthenticatedException;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.exception.InvalidTokenException;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String accountId = jwt.getSubject();
        if (accountId == null) {
            throw new InvalidTokenException();
        }
        Account account = accountRepository.findByIdWithRolesAndPermissions(new AccountId(accountId))
                .orElseThrow(InvalidTokenException::new);

        if(AccountStatus.INACTIVE.equals(account.getStatus()) || account.getDeletedAt() != null) {
            throw new UnAuthenticatedException();
        }

        Set<GrantedAuthority> authorities = account.getEffectivePermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.code()))
                .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(account.getId(), jwt, authorities);
    }
}
