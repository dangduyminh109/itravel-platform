package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.account.CreateAccountByGoogleCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
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
    CustomerRepository customerRepository;
    AccountCommandHandler accountCommandHandler;

    public void RegisterCustomerByGoogle(RegisterCustomerByGoogleCommand command) {
        Customer customer = Customer.create(command.fullName());
        CreateAccountByGoogleCommand createAccountByGoogleCommand =
                new CreateAccountByGoogleCommand(
                        command.email(),
                        customer.getId()
                );
        accountCommandHandler.CreateByGoogle(createAccountByGoogleCommand);
        customerRepository.save(customer);
    }

    public CustomerDetail create(CustomerCreateCommand command) {
        Customer customer = Customer.create(command.fullName());
        CreateAccountByEmailCommand createAccountByEmailCommand
                = new CreateAccountByEmailCommand(
                command.email(),
                command.password(),
                customer.getId()
        );
        Account account = accountCommandHandler.CreateByEmail(createAccountByEmailCommand);

        customerRepository.save(customer);
        return CustomerDetail.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(account.getEmail())
                .status(account.getStatus())
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

        UpdateAccountPasswordCommand updateAccountPasswordCommand =
                new UpdateAccountPasswordCommand(
                        command.id().value(),
                        command.newPassword()
                );

        Account account = accountCommandHandler.ChangePassword(updateAccountPasswordCommand);
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
        accountCommandHandler.destroy(command.id().value());
        customerRepository.destroy(command.id());
    }
}
