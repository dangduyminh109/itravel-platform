package com.itravel.platform.modules.identity.application.command.model.customer;

import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.domain.customer.*;

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
        RawPassword newPassword,
        boolean removeAvatar
) { }
