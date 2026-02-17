package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends BaseAggregate<CustomerId> {
    FullName fullName;

    private Customer(
            FullName fullName
    ) {
        super(CustomerId.generate());
        this.fullName = fullName;
    }

    private Customer(CustomerId id,
                     FullName fullName,
                     Instant createdAt,
                     Instant updatedAt,
                     Instant deletedAt
    ) {
        super(id);
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Customer create(
            FullName fullName
    ) {
        return new Customer(
                fullName
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Customer fromExisting(CustomerId id,
                                       FullName fullName,
                                       Instant createdAt,
                                       Instant updatedAt,
                                       Instant deletedAt
    ) {
        Customer customer =  new Customer(
                id,
                fullName,
                createdAt,
                updatedAt,
                deletedAt
        );
        return customer;
    }
    public void changeName(FullName fullName) {
        this.fullName = fullName;
        touch();
    }
}
