package com.itravel.platform.modules.location.domain.location;

import com.itravel.platform.modules.location.domain.location.exception.InvalidLocationIdException;
import java.util.Objects;

public record LocationId(Long value) {
    public LocationId{
        if(Objects.isNull(value)){
            throw new InvalidLocationIdException();
        }
    }
}
