package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CustomerCreateCommand(
        FullName fullName,
        PhoneNumber phoneNumber,
        MultipartFile avatar,
        Gender gender,
        LocalDate dateOfBirth,
        Address address,
        IdentityCard identityCard,
        Passport passport,
        Email email,
        RawPassword password
) {}
