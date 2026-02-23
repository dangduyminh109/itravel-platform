package com.itravel.platform.modules.identity.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.PastOrPresentDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest{
        @NotBlank(message = "USERNAME_CANNOT_BE_BLANK")
        String username;

        @NotBlank(message = "PASSWORD_CANNOT_BE_BLANK")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "PASSWORD_INVALID")
        String password;

        @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
        String fullName;

        @Pattern(regexp = "^$|^\\+?[0-9]{10,15}$", message = "PHONE_NUMBER_INVALID")
        String phoneNumber;

        MultipartFile avatar;

        @Pattern(regexp = "^$|^(MALE|FEMALE|OTHER)$", message = "GENDER_INVALID")
        String gender;

        @PastOrPresentDate(message = "DATE_OF_BIRTH_MUST_BE_PAST_OR_PRESENT")
        LocalDate dateOfBirth;

        @NotEmpty(message = "ROLE_LIST_CANNOT_BE_EMPTY")
        List<@NotNull(message = "ROLE_ID_CANNOT_BE_NULL") Long> roleList;

        List<@Valid PermissionOverrideRequest> permissionOverrides;
}

