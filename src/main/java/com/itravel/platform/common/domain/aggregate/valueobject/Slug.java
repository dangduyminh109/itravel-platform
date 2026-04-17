package com.itravel.platform.common.domain.aggregate.valueobject;

import com.itravel.platform.common.exceptions.InvalidSlugException;

import java.text.Normalizer;
import java.util.Objects;

public record Slug(String value) {
    public Slug {
        if(Objects.isNull(value) || !value.matches("^[a-z0-9-]+$")){
            throw new InvalidSlugException();
        }
    }

    public static Slug toSlug(String input) {
        if (input == null) {
            throw new InvalidSlugException();
        }

        String slug = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");

        return new Slug(slug);
    }
}
