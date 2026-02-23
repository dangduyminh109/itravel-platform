package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerQueryService {
    CustomerRepository customerRepository;
    AccountLinkRepository accountLinkRepository;
    AccountRepository accountRepository;
    public List<CustomerDetail> getCustomers(){
        return customerRepository.getCustomers().stream()
                .map(customer -> {
                    AccountLink accountLink = accountLinkRepository
                            .findByTargetId(customer.getId().value())
                            .orElseThrow(CustomerNotExistException::new);
                    Account account = accountRepository.findById(accountLink.getAccountId())
                            .orElseThrow(CustomerNotExistException::new);
                    return CustomerDetail.builder()
                            .id(customer.getId())
                            .fullName(customer.getFullName())
                            .phoneNumber(customer.getPhoneNumber())
                            .avatar(customer.getAvatar())
                            .gender(customer.getGender())
                            .dateOfBirth(customer.getDateOfBirth())
                            .address(customer.getAddress())
                            .identityCard(customer.getIdentityCard())
                            .passport(customer.getPassport())
                            .email(account.getEmail())
                            .roleList(account.getRoleList())
                            .status(account.getStatus())
                            .createdAt(customer.getCreatedAt())
                            .updatedAt(customer.getUpdatedAt())
                            .deletedAt(customer.getDeletedAt())
                            .build();
                }).toList();
    }
}
