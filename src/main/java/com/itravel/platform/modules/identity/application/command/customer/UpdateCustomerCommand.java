package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record UpdateCustomerCommand(
        CustomerId id,
        FullName fullName,
        PhoneNumber phoneNumber,
        AccountStatus status,
        MultipartFile avatar,
        Gender gender,
        LocalDate dateOfBirth,
        Address address,
        IdentityCard identityCard,
        Passport passport,
        RawPassword newPassword
) { }
