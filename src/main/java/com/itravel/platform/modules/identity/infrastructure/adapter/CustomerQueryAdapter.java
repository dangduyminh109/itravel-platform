package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerQueryPort;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.CustomerMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountJpaRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountLinkJpaRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.CustomerJpaRepository;
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
public class CustomerQueryAdapter implements CustomerQueryPort {
    CustomerJpaRepository customerJpaRepository;
    AccountLinkJpaRepository accountLinkJpaRepository;
    AccountJpaRepository accountJpaRepository;

    @Override
    public CustomerGeneralInfoDTO getCustomerGeneralInfoDTO() {
        return customerJpaRepository.getCustomerGeneralInfoDTO();
    }

    @Override
    public Page<CustomerDetailDTO> getCustomers(String keyword, Pageable pageable, boolean isDeleted) {
        return customerJpaRepository
                .searchCustomer(keyword, pageable, isDeleted)
                .map(entity -> {
                    AccountLinkJpaEntity link = accountLinkJpaRepository.findByTargetId(entity.getId())
                            .orElse(null);
                    AccountJpaEntity account = (link != null) ? 
                            accountJpaRepository.findById(link.getAccountId()).orElse(null) : null;
                    return CustomerMapper.toCustomerDetailDTO(entity, account);
                });
    }

    @Override
    public Optional<CustomerDetailDTO> getCustomer(String id) {
        return customerJpaRepository.findById(id)
                .map(customerEntity -> {
                    AccountLinkJpaEntity link = accountLinkJpaRepository.findByTargetId(id)
                            .orElse(null);
                    
                    AccountJpaEntity account = (link != null) ? 
                            accountJpaRepository.findById(link.getAccountId()).orElse(null) : null;

                    return CustomerMapper.toCustomerDetailDTO(customerEntity, account);
                });
    }
}
