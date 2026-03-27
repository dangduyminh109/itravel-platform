package com.itravel.platform.modules.location.domain.aggregate.valueobject;

import com.itravel.platform.modules.location.domain.exception.InvalidLocationNameException;
import com.itravel.platform.modules.location.domain.exception.LocationNameTooLongException;

import java.util.Objects;

public record LocationName(String value) {
    public LocationName {
        if(Objects.isNull(value) || value.isBlank()){
            throw new InvalidLocationNameException();
        }
        if (value.length() > 50){
            throw new LocationNameTooLongException();
        }
    }
}
