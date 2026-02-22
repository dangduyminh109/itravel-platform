package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerCreateRequest(
   @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
   String fullName,

   @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
   @Email(message = "EMAIL_INVALID")
   String email,

   @NotBlank(message = "PASSWORD_CANNOT_BE_BLANK")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "PASSWORD_INVALID")
   String password
) {}
