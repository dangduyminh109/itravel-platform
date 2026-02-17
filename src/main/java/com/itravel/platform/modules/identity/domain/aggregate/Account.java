package com.itravel.platform.modules.identity.domain.aggregate;
import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import com.itravel.platform.modules.identity.domain.exception.EmailCredentialsRequiredException;
import com.itravel.platform.modules.identity.domain.exception.GoogleCredentialsRequiredException;
import com.itravel.platform.modules.identity.domain.exception.UsernameCredentialsRequiredException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseAggregate<AccountId> {
    Username username;
    Email email;
    PasswordHash password;
    AuthProvider authProvider;
    AccountStatus status;

    private Account (
            Username username,
            Email email,
            PasswordHash password,
            AuthProvider authProvider
    ){
        super(AccountId.generate());
        this.email = email;
        this.username= username;
        this.password = password;
        this.authProvider = authProvider;
        this.status = AccountStatus.ACTIVE;
        validateInvariant();
    }

    private Account (
            AccountId id,
            Username username,
            Email email,
            PasswordHash password,
            AuthProvider authProvider,
            AccountStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ){
        super(
                id,
                createdAt,
                updatedAt,
                deletedAt
        );
        this.email = email;
        this.username= username;
        this.password = password;
        this.authProvider = authProvider;
        this.status = status;
    }

    public static Account createByUsername(
            Username username,
            PasswordHash password
    ) {
        return new Account(username, null, password, AuthProvider.USERNAME);
    }

    public static Account createByEmail(
            Email email,
            PasswordHash password
    ) {
        return new Account(null, email, password, AuthProvider.EMAIL);
    }

    public static Account createByGoogle(Email email) {
        return new Account(null, email, null, AuthProvider.GOOGLE);
    }

    public void changePassword(PasswordHash newPassword) {
        if (authProvider == AuthProvider.GOOGLE) {
            throw new IllegalStateException("Google account cannot have password");
        }
        this.password = newPassword;
        touch();
    }

    private void validateInvariant() {
        switch (authProvider) {
            case USERNAME -> {
                if (username == null || password == null) {
                    throw new UsernameCredentialsRequiredException();
                }
            }
            case EMAIL -> {
                if (email == null || password == null) {
                    throw new EmailCredentialsRequiredException();
                }
            }
            case GOOGLE -> {
                if (email == null) {
                    throw new GoogleCredentialsRequiredException();
                }
            }
        }
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Account fromExisting(
            AccountId id,
            Username username,
            Email email,
            PasswordHash password,
            AuthProvider authProvider,
            AccountStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        Account acc = new Account(id,
                username,
                email,
                password,
                authProvider,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
        return acc;
    }
}
