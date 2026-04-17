package com.itravel.platform.modules.identity.application.command.service.customer;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountStatusCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.UpdateCustomerCommand;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.user.Avatar;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import com.itravel.platform.modules.identity.application.port.in.customer.UpdateCustomerUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.AddressDTO;
import com.itravel.platform.modules.identity.application.dto.IdentityCardDTO;
import com.itravel.platform.modules.identity.application.dto.PassportDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.role.Permission;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateCustomerService implements UpdateCustomerUseCase {
    CustomerRepository customerRepository;
    AccountCommandFacade accountCommandHandler;
    MediaUploadPort mediaUploadPort;

    @Transactional
    public CustomerDetailDTO execute(UpdateCustomerCommand command){
        Customer customer = customerRepository.findById(command.id())
                .orElseThrow(CustomerNotExistException::new);

        UpdateAccountPasswordCommand updateAccountPasswordCommand =
                new UpdateAccountPasswordCommand(
                        command.id().value(),
                        command.newPassword()
                );

        accountCommandHandler.changePassword(updateAccountPasswordCommand);

        UpdateAccountStatusCommand updateAccountStatusCommand =
                new UpdateAccountStatusCommand(
                        command.id().value(),
                        command.status()
                );

        Account account = accountCommandHandler.changeStatus(updateAccountStatusCommand);

        customer.changeName(command.fullName());
        customer.changePhoneNumber(command.phoneNumber());
        customer.changeGender(command.gender());
        customer.changeDateOfBirth(command.dateOfBirth());
        customer.changeAddress(command.address());
        customer.changeIdentityCard(command.identityCard());
        customer.changePassport(command.passport());

        if(command.removeAvatar()){
            customer.changeAvatar(null);
        }

        if (command.avatar() != null && !command.avatar().isEmpty()) {
            String avatarUrl = mediaUploadPort.uploadAvatar(command.avatar());
            customer.changeAvatar(new Avatar(avatarUrl));
        }

        customerRepository.save(customer);
        return CustomerDetailDTO.builder()
                .id(customer.getId().value())
                .fullName(customer.getFullName().value())
                .phoneNumber(customer.getPhoneNumber() != null ? customer.getPhoneNumber().value() : null)
                .avatar(customer.getAvatar() != null ? customer.getAvatar().value() : null)
                .gender(customer.getGender() != null ? customer.getGender().name() : null)
                .dateOfBirth(customer.getDateOfBirth())
                .address(customer.getAddress() != null ? AddressDTO.builder()
                        .detail(customer.getAddress().detail())
                        .wardId(customer.getAddress().wardId())
                        .provinceId(customer.getAddress().provinceId())
                        .build() : null)
                .identityCard(customer.getIdentityCard() != null ? IdentityCardDTO.builder()
                        .documentNumber(customer.getIdentityCard().documentNumber())
                        .issueDate(customer.getIdentityCard().issueDate())
                        .issuePlace(customer.getIdentityCard().issuePlace())
                        .build() : null)
                .passport(customer.getPassport() != null ? PassportDTO.builder()
                        .documentNumber(customer.getPassport().documentNumber())
                        .issueDate(customer.getPassport().issueDate())
                        .expiryDate(customer.getPassport().expiryDate())
                        .build() : null)
                .email(account.getEmail().value())
                .status(account.getStatus().name())
                .roleList(account.getRoleList().stream()
                        .map(r -> RoleDTO.builder()
                                .id(r.getId().value())
                                .name(r.getName().value())
                                .status(r.getStatus().name())
                                .permissionList(r.getPermissionList().stream()
                                        .map(Permission::code)
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .deletedAt(customer.getDeletedAt())
                .build();
    }
}

