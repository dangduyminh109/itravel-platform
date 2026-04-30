package com.itravel.platform.modules.tour.application.dto;

import java.util.ArrayList;
import java.util.List;

public record ServicesDTO(
        List<String> includes,
        List<String> excludes
) {
    public ServicesDTO(List<String> includes, List<String> excludes) {
        this.includes = includes != null ? includes : new ArrayList<>();
        this.excludes = excludes != null ? excludes : new ArrayList<>();
    }
}
