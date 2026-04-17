package com.itravel.platform.modules.identity.application.command.service.customer;
import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.CustomerCreateCommand;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.user.Avatar;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.AddressDTO;
import com.itravel.platform.modules.identity.application.dto.IdentityCardDTO;
import com.itravel.platform.modules.identity.application.dto.PassportDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.role.Permission;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.customer.CreateCustomerUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateCustomerService implements CreateCustomerUseCase {
    CustomerRepository customerRepository;
    AccountCommandFacade accountCommandHandler;
    MediaUploadPort mediaUploadPort;
    
    @Transactional
    public CustomerDetailDTO execute(CustomerCreateCommand command) {
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
        Account account = accountCommandHandler.createByEmail(createAccountByEmailCommand);

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

