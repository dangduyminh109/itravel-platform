package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseAggregate<UserId> {
    FullName fullName;
    PhoneNumber phoneNumber;
    Avatar avatar;
    Gender gender;
    Email email;
    LocalDate dateOfBirth;

    private User(
            FullName fullName,
            PhoneNumber phoneNumber,
            Avatar avatar,
            Gender gender,
            Email email,
            LocalDate dateOfBirth
    ) {
        super(UserId.generate());
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    private User(UserId id,
                 FullName fullName,
                 PhoneNumber phoneNumber,
                 Avatar avatar,
                 Gender gender,
                 Email email,
                 LocalDate dateOfBirth,
                 Instant createdAt,
                 Instant updatedAt,
                 Instant deletedAt
    ) {
        super(id);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static User create(
            FullName fullName,
            PhoneNumber phoneNumber,
            Avatar avatar,
            Gender gender,
            Email email,
            LocalDate dateOfBirth
    ) {
        return new User(
                fullName,
                phoneNumber,
                avatar,
                gender,
                email,
                dateOfBirth
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static User fromExisting(UserId id,
                                    FullName fullName,
                                    PhoneNumber phoneNumber,
                                    Avatar avatar,
                                    Gender gender,
                                    Email email,
                                    LocalDate dateOfBirth,
                                    Instant createdAt,
                                    Instant updatedAt,
                                    Instant deletedAt
    ) {
        return new User(
                id,
                fullName,
                phoneNumber,
                avatar,
                gender,
                email,
                dateOfBirth,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateName(FullName fullName) {
        this.fullName = fullName;
        touch();
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        touch();
    }

    public void updateAvatar(Avatar avatar) {
        this.avatar = avatar;
        touch();
    }

    public void updateGender(Gender gender) {
        this.gender = gender;
        touch();
    }

    public void updateDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        touch();
    }

    public void updateEmail(Email email) {
        this.email = email;
        touch();
    }
}
