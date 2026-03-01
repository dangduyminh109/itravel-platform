package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.common.utils.SecurityUtils;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.api.mapper.CustomerRestMapper;
import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.exception.AccountInActiveException;
import com.itravel.platform.modules.identity.application.exception.CustomerDeletedException;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerQueryService {
    CustomerRepository customerRepository;
    AccountLinkRepository accountLinkRepository;
    AccountRepository accountRepository;
    CustomerRestMapper mapper;

    public PageResponse<CustomerResponse> getCustomers(String keyword, Pageable pageable) {
        Page<Customer> responsePage = customerRepository.getCustomers(keyword,pageable);

        return PageResponse.<CustomerResponse>builder()
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalPages(responsePage.getTotalPages())
                .totalElements(responsePage.getTotalElements())
                .data(
                        responsePage.getContent().stream()
                                .map(customer -> {
                                    AccountLink accountLink = accountLinkRepository
                                            .findByTargetId(customer.getId().value())
                                            .orElseThrow(CustomerNotExistException::new);
                                    Account account = accountRepository.findById(accountLink.getAccountId())
                                            .orElseThrow(CustomerNotExistException::new);
                                    return mapper.toCustomerResponse(
                                                createCustomerDetail(customer, account)
                                            );
                                }).toList()
                )
                .build();
    }

    public CustomerDetail getMe() {
        String accountId = SecurityUtils.getCurrentAccountId();
        AccountLink accountLink = accountLinkRepository
                .findByAccountId(new AccountId(accountId))
                .orElseThrow(CustomerNotExistException::new);
        Customer customer = customerRepository.findById(new CustomerId(accountLink.getTargetId()))
                .orElseThrow(CustomerNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(CustomerNotExistException::new);

        if(customer.getDeletedAt() != null || account.getDeletedAt() != null){
            throw new CustomerDeletedException();
        }
        if(AccountStatus.INACTIVE.equals(account.getStatus())){
            throw new AccountInActiveException();
        }

        return createCustomerDetail(customer, account);
    }

    private CustomerDetail createCustomerDetail(Customer customer, Account account) {
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
    }
}
