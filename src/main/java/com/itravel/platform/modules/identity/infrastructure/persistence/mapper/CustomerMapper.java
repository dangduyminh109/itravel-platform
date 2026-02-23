package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    @Mapping(target = "addressDetail", source = "address.detail")
    @Mapping(target = "wardId", source = "address.wardId")
    @Mapping(target = "provinceId", source = "address.provinceId")
    @Mapping(target = "identityCardNumber", source = "identityCard.documentNumber")
    @Mapping(target = "identityCardIssueDate", source = "identityCard.issueDate")
    @Mapping(target = "identityCardIssuePlace", source = "identityCard.issuePlace")
    @Mapping(target = "passportNumber", source = "passport.documentNumber")
    @Mapping(target = "passportIssueDate", source = "passport.issueDate")
    @Mapping(target = "passportExpiryDate", source = "passport.expiryDate")
    CustomerJpaEntity toCustomerJpaEntity(Customer customer);

    static Customer toCustomerDomain(CustomerJpaEntity entity) {
        Address address = null;
        if (entity.getProvinceId() != null) {
            address = new Address(
                    entity.getAddressDetail(),
                    entity.getWardId(),
                    entity.getProvinceId()
            );
        }

        IdentityCard identityCard = null;
        if (entity.getIdentityCardNumber() != null && !entity.getIdentityCardNumber().isBlank()) {
            identityCard = new IdentityCard(
                    entity.getIdentityCardNumber(),
                    entity.getIdentityCardIssueDate(),
                    entity.getIdentityCardIssuePlace()
            );
        }

        Passport passport = null;
        if (entity.getPassportNumber() != null && !entity.getPassportNumber().isBlank()) {
            passport = new Passport(
                    entity.getPassportNumber(),
                    entity.getPassportIssueDate(),
                    entity.getPassportExpiryDate()
            );
        }

        return Customer.fromExistingBuilder()
                .id(new CustomerId(entity.getId()))
                .fullName(new FullName(entity.getFullName()))
                .phoneNumber(entity.getPhoneNumber() != null ? new PhoneNumber(entity.getPhoneNumber()) : null)
                .avatar(entity.getAvatar() != null ? new Avatar(entity.getAvatar()) : null)
                .gender(entity.getGender())
                .dateOfBirth(entity.getDateOfBirth())
                .address(address)
                .identityCard(identityCard)
                .passport(passport)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
