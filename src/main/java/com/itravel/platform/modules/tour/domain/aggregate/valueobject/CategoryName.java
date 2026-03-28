package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.CategoryNameTooLongException;
import com.itravel.platform.modules.tour.domain.exception.InvalidCategoryNameException;

import java.util.Objects;

public record CategoryName(String value) {
    public CategoryName {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new InvalidCategoryNameException();
        }
        if (value.length() > 50) {
            throw new CategoryNameTooLongException();
        }
    }
}
