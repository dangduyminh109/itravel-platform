package com.itravel.platform.modules.tour.api.dto.response;

import java.util.ArrayList;
import java.util.List;

public record ServicesResponse(
        List<String> includes,
        List<String> excludes
) {
    public ServicesResponse(List<String> includes, List<String> excludes) {
        this.includes = includes != null ? includes : new ArrayList<>();
        this.excludes = excludes != null ? excludes : new ArrayList<>();
    }
}
