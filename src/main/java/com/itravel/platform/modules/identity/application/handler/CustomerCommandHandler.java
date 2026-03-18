package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountStatusCommand;
import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Avatar;
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
    MediaUploadPort mediaUploadPort;

    public CustomerDetail create(CustomerCreateCommand command) {
        Avatar avatar = null;
        if (command.avatar() != null && !command.avatar().isEmpty()) {
            String avatarUrl = mediaUploadPort.uploadAvatar(command.avatar());

            avatar = new Avatar(avatarUrl);
        }

        Customer customer = Customer.create(
                command.fullName(),
                command.phoneNumber(),
                avatar,
                command.gender(),
                command.dateOfBirth(),
                command.address(),
                command.identityCard(),
                command.passport()
        );
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
                .phoneNumber(customer.getPhoneNumber())
                .avatar(customer.getAvatar())
                .gender(customer.getGender())
                .dateOfBirth(customer.getDateOfBirth())
                .address(customer.getAddress())
                .identityCard(customer.getIdentityCard())
                .passport(customer.getPassport())
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

        accountCommandHandler.ChangePassword(updateAccountPasswordCommand);

        UpdateAccountStatusCommand updateAccountStatusCommand =
                new UpdateAccountStatusCommand(
                        command.id().value(),
                        command.status()
                );

        Account account = accountCommandHandler.ChangeStatus(updateAccountStatusCommand);

        customer.changeName(command.fullName());
        customer.changePhoneNumber(command.phoneNumber());
        customer.changeGender(command.gender());
        customer.changeDateOfBirth(command.dateOfBirth());
        customer.changeAddress(command.address());
        customer.changeIdentityCard(command.identityCard());
        customer.changePassport(command.passport());

        if (command.avatar() != null && !command.avatar().isEmpty()) {
            String avatarUrl = mediaUploadPort.uploadAvatar(command.avatar());
            customer.changeAvatar(new Avatar(avatarUrl));
        }

        customerRepository.save(customer);
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
                .status(account.getStatus())
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
        accountCommandHandler.restore(command.id().value());
        customer.restore();
        customerRepository.save(customer);
    }

    @Transactional
    public void destroy(DeleteCustomerCommand command){
        accountCommandHandler.destroy(command.id().value());
        customerRepository.destroy(command.id());
    }
}
