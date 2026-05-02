package com.itravel.platform.modules.booking.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.booking.domain.passenger.FullName;
import com.itravel.platform.modules.booking.domain.passenger.Passenger;
import com.itravel.platform.modules.booking.domain.passenger.PassengerId;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.PassengerJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PassengerMapper {

    static PassengerJpaEntity toPassengerJpaEntity(Passenger domain) {
        if (domain == null) return null;
        
        return PassengerJpaEntity.builder()
                .id(domain.getId() != null ? domain.getId().value() : null)
                .fullName(domain.getFullName() != null ? domain.getFullName().value() : null)
                .dateOfBirth(domain.getDateOfBirth())
                .gender(domain.getGender() != null ? domain.getGender().name() : null)
                .identityNumber(domain.getIdentityNumber())
                .passengerType(domain.getPassengerType())
                .build();
    }

    static Passenger toPassengerDomain(PassengerJpaEntity entity) {
        if (entity == null) return null;

        return Passenger.fromExisting(
                entity.getId() != null ? new PassengerId(entity.getId()) : null,
                entity.getFullName() != null ? new FullName(entity.getFullName()) : null,
                entity.getDateOfBirth(),
                entity.getGender() != null ? Gender.valueOf(entity.getGender()) : null,
                entity.getIdentityNumber(),
                entity.getPassengerType()
        );
    }
}
