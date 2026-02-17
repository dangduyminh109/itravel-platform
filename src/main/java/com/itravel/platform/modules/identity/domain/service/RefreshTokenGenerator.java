package com.itravel.platform.modules.identity.domain.service;
import org.springframework.stereotype.Component;
@Component
public interface RefreshTokenGenerator {
    String generate();
}
