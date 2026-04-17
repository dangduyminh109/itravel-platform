package com.itravel.platform.modules.tour.domain.category;

import com.itravel.platform.modules.tour.domain.category.exception.CategoryNameTooLongException;
import com.itravel.platform.modules.tour.domain.category.exception.InvalidCategoryNameException;

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
