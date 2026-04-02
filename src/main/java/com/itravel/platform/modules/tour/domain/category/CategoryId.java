package com.itravel.platform.modules.tour.domain.category;

import com.itravel.platform.modules.tour.domain.category.exception.InvalidCategoryIdException;
import java.util.Objects;

public record CategoryId(Long value) {
    public CategoryId {
        if (Objects.isNull(value)) {
            throw new InvalidCategoryIdException();
        }
    }
}
