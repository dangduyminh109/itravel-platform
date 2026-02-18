package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.modules.identity.application.exception.AccountNotExistException;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    CustomerRepository customerRepository;
    AccountLinkRepository accountLinkRepository;
    AccountRepository accountRepository;

    public Set<String> getPermissions(AccountId id){
        Account account = accountRepository.findById(id)
                .orElseThrow(AccountNotExistException::new);

        Set<Role> roleList = new HashSet<>(account.getRoleList());
        Set<Permission> permissionList = new HashSet<>();

        for(Role r : roleList){
            permissionList.addAll(r.getPermissionList());
        }
        return permissionList.stream().map(Permission::code)
                .collect(Collectors.toSet());
    }
}
