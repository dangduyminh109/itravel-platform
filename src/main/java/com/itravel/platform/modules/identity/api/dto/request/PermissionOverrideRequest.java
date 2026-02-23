package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionOverrideRequest {

    @NotBlank(message = "PERMISSION_CANNOT_BE_BLANK")
    String permission;

    @NotBlank(message = "PERMISSION_TYPE_CANNOT_BE_BLANK")
    @Pattern(regexp = "^$|^(GRANT|DENY)$", message = "PERMISSION_TYPE_INVALID")
    String permissionType;
}