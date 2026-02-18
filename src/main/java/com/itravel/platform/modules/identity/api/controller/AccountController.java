package com.itravel.platform.modules.identity.api.controller;
import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.application.service.AccountQueryService;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/account")
public class AccountController {
    AccountQueryService accountQueryService;

    @GetMapping("/{id}")
    public ApiResponse<Set<String>> getPermission(@PathVariable String id) {
        return ApiResponse.<Set<String>>builder()
                .success(true)
                .data(accountQueryService.getPermissions(new AccountId(id)))
                .build();
    }
}
