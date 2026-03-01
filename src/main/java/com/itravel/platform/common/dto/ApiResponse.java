package com.itravel.platform.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse<T>
{
    Boolean success;
    String message;
    T response;
    String RequestId;

    @Builder.Default
    List<ApiError> errors = new ArrayList<>();
    @Builder.Default
    Instant Timestamp = Instant.now();
}