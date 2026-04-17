package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByEmailUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RegisterCustomerByEmailUseCase;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import com.itravel.platform.modules.identity.application.port.out.otp.OtpRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.otp.Otp;
import com.itravel.platform.modules.identity.domain.otp.exception.InvalidOtpCodeException;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.role.Permission;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterCustomerByEmailService implements RegisterCustomerByEmailUseCase {
    OtpRepository otpRepository;
    CustomerRepository customerRepository;
    CreateAccountByEmailUseCase createAccountByEmailUseCase;

    @Override
    @Transactional
    public CustomerDetailDTO execute(RegisterCustomerByEmailCommand command) {
        Otp otp = otpRepository.findByEmailAndCode(command.email(), command.otp())
                .orElseThrow(InvalidOtpCodeException::new);
        otp.verify(command.otp());

        Customer customer = Customer.create(
                command.fullName(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        CreateAccountByEmailCommand createAccountByEmailCommand
                = new CreateAccountByEmailCommand(
                command.email(),
                command.password(),
                customer.getId()
        );
        Account account = createAccountByEmailUseCase.execute(createAccountByEmailCommand);
        customerRepository.save(customer);
        otpRepository.destroy(otp.getId());
        return CustomerDetailDTO.builder()
                .id(customer.getId().value())
                .fullName(customer.getFullName().value())
                .phoneNumber(customer.getPhoneNumber() != null ? customer.getPhoneNumber().value() : null)
                .avatar(customer.getAvatar() != null ? customer.getAvatar().value() : null)
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
