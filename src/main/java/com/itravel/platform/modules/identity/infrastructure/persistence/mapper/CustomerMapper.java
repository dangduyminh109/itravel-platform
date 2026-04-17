package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.user.FullName;
import com.itravel.platform.modules.identity.domain.user.PhoneNumber;
import com.itravel.platform.modules.identity.domain.user.Avatar;
import com.itravel.platform.modules.identity.application.dto.AddressDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.IdentityCardDTO;
import com.itravel.platform.modules.identity.application.dto.PassportDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.customer.Address;
import com.itravel.platform.modules.identity.domain.customer.IdentityCard;
import com.itravel.platform.modules.identity.domain.customer.Passport;
import com.itravel.platform.modules.identity.domain.customer.CustomerId;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import java.util.Set;
import java.util.stream.Collectors;
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

    static CustomerDetailDTO toCustomerDetailDTO(CustomerJpaEntity entity, AccountJpaEntity account) {
        return CustomerDetailDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .avatar(entity.getAvatar())
                .gender(entity.getGender() != null ? entity.getGender().name() : null)
                .dateOfBirth(entity.getDateOfBirth())
                .address(new AddressDTO(entity.getAddressDetail(), entity.getWardId(), entity.getProvinceId()))
                .identityCard(new IdentityCardDTO(entity.getIdentityCardNumber(), entity.getIdentityCardIssueDate(), entity.getIdentityCardIssuePlace()))
                .passport(new PassportDTO(entity.getPassportNumber(), entity.getPassportIssueDate(), entity.getPassportExpiryDate()))
                .email(account != null ? account.getEmail() : null)
                .status(account != null ? account.getStatus().name() : null)
                .roleList(account != null ? mapRoles(account) : Set.of())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    static Set<RoleDTO> mapRoles(AccountJpaEntity account) {
        return account.getRoleList().stream()
                .map(role -> RoleDTO.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .status(role.getStatus() != null ? role.getStatus() : null)
                        .permissionList(role.getPermissionList().stream().map(p -> p.getCode()).collect(Collectors.toSet()))
                        .build()
                )
                .collect(Collectors.toSet());
    }
}