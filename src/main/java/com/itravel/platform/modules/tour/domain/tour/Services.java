package com.itravel.platform.modules.tour.domain.tour;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.itravel.platform.modules.tour.domain.tour.exception.ServicesOverlapException;

public record Services(List<String> includes, List<String> excludes) {
    public Services {
        includes = includes == null ? Collections.emptyList() : List.copyOf(includes);
        excludes = excludes == null ? Collections.emptyList() : List.copyOf(excludes);
        validateNoOverlap(includes, excludes);
    }
    private static void validateNoOverlap(List<String> inc, List<String> exc) {
        for (String includeItem : inc) {
            String normalizedInclude = includeItem.trim().toLowerCase();

            boolean isConflict = exc.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .anyMatch(excludeItem -> excludeItem.equals(normalizedInclude));

            if (isConflict) {
                throw new ServicesOverlapException();
            }
        }
    }

    public Services addInclude(String newService) {
        List<String> newIncludes = new ArrayList<>(this.includes());
        newIncludes.add(newService);
        return new Services(newIncludes, this.excludes());
    }
}