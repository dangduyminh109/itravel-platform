package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    @Mapping(target = "id",
            expression = "java(customer.getId() != null ? customer.getId().value() : null)")
    @Mapping(target = "fullName",
            expression = "java(customer.getFullName() != null ? customer.getFullName().value() : null)")
    CustomerJpaEntity toCustomerJpaEntity(Customer customer);

    static Customer toCustomerDomain(CustomerJpaEntity entity) {
        return Customer.fromExistingBuilder()
                .id(new CustomerId(entity.getId()))
                .fullName(new FullName(entity.getFullName()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
