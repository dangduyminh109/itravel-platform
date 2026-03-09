package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.modules.identity.api.dto.response.PermissionResponse;
import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionQueryService {
    public List<PermissionResponse> getPermissions(){
         return Arrays.stream(PermissionCode.values())
                 .map(item -> new PermissionResponse(item.getCode(),item.getDescription())).toList();
    }
}
