package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.exception.EmailExistedException;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import com.itravel.platform.modules.identity.domain.repository.RoleRepository;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerCommandHandler {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;

    public void RegisterCustomerByGoogle(RegisterCustomerByGoogleCommand command) {
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByGoogle(command.email(),role);
        Customer customer = Customer.create(command.fullName());

        AccountLink accountLink = AccountLink.linkToSystemUser(account.getId(), customer.getId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        customerRepository.save(customer);
    }


    public CustomerDetail RegisterCustomerByEmail(RegisterCustomerByEmailCommand command) {
        accountRepository.findByEmail(command.email()).ifPresent(e -> {
            throw new EmailExistedException();
        });
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByEmail(command.email(), passwordEncoderAdapter.encode(command.password().value()),role);
        Customer customer = Customer.create(command.fullName());
        AccountLink accountLink = AccountLink.linkToCustomer(account.getId(), customer.getId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        customerRepository.save(customer);
        return CustomerDetail.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(account.getEmail())
                .roleList(account.getRoleList())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .deletedAt(customer.getDeletedAt())
                .build();
    }

    @Transactional
    public CustomerDetail update(UpdateCustomerCommand command){
        Customer customer = customerRepository.findById(command.id())
                .orElseThrow(CustomerNotExistException::new);

        AccountLink accountLink = accountLinkRepository.findByTargetId(customer.getId().value())
                .orElseThrow(CustomerNotExistException::new);

        Account account = accountRepository.findById(accountLink.getAccountId())
                        .orElseThrow(CustomerNotExistException::new);

        customer.changeName(command.fullName());
        customerRepository.save(customer);
        return CustomerDetail.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(account.getEmail())
                .roleList(account.getRoleList())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .deletedAt(customer.getDeletedAt())
                .build();
    }

    @Transactional
    public void delete(DeleteCustomerCommand command){
        Optional<Customer> optionalCustomer = customerRepository.findById(command.id());
        if(optionalCustomer.isEmpty()){
            return;
        }
        Customer customer = optionalCustomer.get();
        customer.softDelete();
        customerRepository.save(customer);
    }

    @Transactional
    public void restore(RestoreCustomerCommand command){
        Customer customer = customerRepository.findById(command.id())
                .orElseThrow(CustomerNotExistException::new);
        customer.restore();
        customerRepository.save(customer);
    }

    @Transactional
    public void destroy(DeleteCustomerCommand command){
        AccountLink accountLink = accountLinkRepository.findByTargetId(command.id().value())
                .orElseThrow(CustomerNotExistException::new);

        accountRepository.destroy(accountLink.getAccountId());
        accountLinkRepository.destroy(accountLink.getId());
        customerRepository.destroy(command.id());
    }
}
