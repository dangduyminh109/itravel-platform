package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.dto.UserGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.port.out.user.UserQueryPort;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.UserMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountJpaRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountLinkJpaRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.UserJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryAdapter implements UserQueryPort {
    UserJpaRepository userJpaRepository;
    AccountLinkJpaRepository accountLinkJpaRepository;
    AccountJpaRepository accountJpaRepository;

    @Override
    public UserGeneralInfoDTO getUserGeneralInfoDTO() {
        return userJpaRepository.getUserGeneralInfoDTO();
    }

    @Override
    public Page<UserDetailDTO> getUsers(String keyword, Pageable pageable, boolean isDeleted) {
        return userJpaRepository
                .getUsers(keyword, pageable, isDeleted)
                .map(entity -> {
                    AccountLinkJpaEntity link = accountLinkJpaRepository.findByTargetId(entity.getId())
                            .orElse(null);
                    AccountJpaEntity account = (link != null) ? 
                            accountJpaRepository.findById(link.getAccountId()).orElse(null) : null;
                    return UserMapper.toUserDetailDTO(entity, account);
                });
    }

    @Override
    public Optional<UserDetailDTO> getUser(String id) {
        return userJpaRepository.findById(id)
                .map(userEntity -> {
                    AccountLinkJpaEntity link = accountLinkJpaRepository.findByTargetId(id)
                            .orElse(null);
                    
                    AccountJpaEntity account = (link != null) ?
                            accountJpaRepository.findById(link.getAccountId()).orElse(null) : null;

                    return UserMapper.toUserDetailDTO(userEntity, account);
                });
    }
}
