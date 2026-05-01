package com.itravel.platform.modules.identity.application.command.model.customer;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.domain.customer.*;

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
