package com.itravel.platform.modules.tour.api.dto.request;

import java.util.List;

public record ServicesRequest(
        List<String> includes,
        List<String> excludes
) {}