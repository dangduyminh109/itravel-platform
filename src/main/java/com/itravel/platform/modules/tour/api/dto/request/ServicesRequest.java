package com.itravel.platform.modules.tour.api.dto.request;

import java.util.List;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServicesRequest {
    @Valid
    List<String> includes = new ArrayList<>();
    @Valid
    List<String> excludes = new ArrayList<>();
}