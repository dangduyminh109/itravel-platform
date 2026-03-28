package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Address;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Avatar;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.IdentityCard;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Passport;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PhoneNumber;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends SoftDeletableAggregate<CustomerId> {
    FullName fullName;
    PhoneNumber phoneNumber;
    Avatar avatar;
    Gender gender;
    LocalDate dateOfBirth;
    Address address;
    IdentityCard identityCard;
    Passport passport;

    private Customer(
            FullName fullName,
            PhoneNumber phoneNumber,
            Avatar avatar,
            Gender gender,
            LocalDate dateOfBirth,
            Address address,
            IdentityCard identityCard,
            Passport passport
    ) {
        super(CustomerId.generate());
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.identityCard = identityCard;
        this.passport = passport;
    }

    private Customer(CustomerId id,
                     FullName fullName,
                     PhoneNumber phoneNumber,
                     Avatar avatar,
                     Gender gender,
                     LocalDate dateOfBirth,
                     Address address,
                     IdentityCard identityCard,
                     Passport passport,
                     Instant createdAt,
                     Instant updatedAt,
                     Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.identityCard = identityCard;
        this.passport = passport;
    }

    public static Customer create(
            FullName fullName,
            PhoneNumber phoneNumber,
            Avatar avatar,
            Gender gender,
            LocalDate dateOfBirth,
            Address address,
            IdentityCard identityCard,
            Passport passport
    ) {
        return new Customer(
                fullName,
                phoneNumber,
                avatar,
                gender,
                dateOfBirth,
                address,
                identityCard,
                passport
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Customer fromExisting(CustomerId id,
                                       FullName fullName,
                                       PhoneNumber phoneNumber,
                                       Avatar avatar,
                                       Gender gender,
                                       LocalDate dateOfBirth,
                                       Address address,
                                       IdentityCard identityCard,
                                       Passport passport,
                                       Instant createdAt,
                                       Instant updatedAt,
                                       Instant deletedAt
    ) {
        return new Customer(
                id,
                fullName,
                phoneNumber,
                avatar,
                gender,
                dateOfBirth,
                address,
                identityCard,
                passport,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void changeName(FullName fullName) {
        this.fullName = fullName;
        touch();
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        touch();
    }

    public void changeAvatar(Avatar avatar) {
        this.avatar = avatar;
        touch();
    }

    public void changeGender(Gender gender) {
        this.gender = gender;
        touch();
    }

    public void changeDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        touch();
    }

    public void changeAddress(Address address) {
        this.address = address;
        touch();
    }

    public void changeIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
        touch();
    }

    public void changePassport(Passport passport) {
        this.passport = passport;
        touch();
    }
}
