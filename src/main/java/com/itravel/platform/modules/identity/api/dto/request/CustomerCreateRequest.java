package com.itravel.platform.modules.identity.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.PastOrPresentDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CustomerCreateRequest(
   @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
   String fullName,

   @Pattern(regexp = "^$|^\\+?[0-9]{10,15}$", message = "PHONE_NUMBER_INVALID")
   String phoneNumber,

   MultipartFile avatar,

   @Pattern(regexp = "^$|^(MALE|FEMALE|OTHER)$", message = "GENDER_INVALID")
   String gender,

   @PastOrPresentDate(message = "DATE_OF_BIRTH_MUST_BE_PAST_OR_PRESENT")
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   LocalDate dateOfBirth,

   @Valid
   AddressRequest address,

   @Valid
   IdentityCardRequest identityCard,

   @Valid
   PassportRequest passport,

   @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
   @Email(message = "EMAIL_INVALID")
   String email,

   @NotBlank(message = "PASSWORD_CANNOT_BE_BLANK")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "PASSWORD_INVALID")
   String password
) {}
