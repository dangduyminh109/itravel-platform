package com.itravel.platform.modules.location.domain.aggregate.valueobject;

import com.itravel.platform.modules.location.domain.exception.InvalidLocationIdException;
import java.util.Objects;

public record LocationId(Long value) {
    public LocationId{
        if(Objects.isNull(value)){
            throw new InvalidLocationIdException();
        }
    }
}
