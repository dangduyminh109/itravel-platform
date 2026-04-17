package com.itravel.platform.modules.identity.domain.account;
import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.modules.identity.application.exception.RoleInActiveException;
import com.itravel.platform.modules.identity.application.exception.UserNotDeleteOrUpdateException;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.role.PermissionOverride;
import com.itravel.platform.modules.identity.domain.role.PermissionType;
import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.RoleStatus;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.account.exception.EmailCredentialsRequiredException;
import com.itravel.platform.modules.identity.domain.account.exception.GoogleCredentialsRequiredException;
import com.itravel.platform.modules.identity.domain.customer.exception.InvalidCustomerRoleException;
import com.itravel.platform.modules.identity.domain.account.exception.UsernameCredentialsRequiredException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends SoftDeletableAggregate<AccountId> {
    Username username;
    Email email;
    PasswordHash password;
    AuthProvider authProvider;
    ProviderId providerId;
    final Set<Role> roleList = new HashSet<>();
    final Set<PermissionOverride> permissionOverrides = new HashSet<>();
    AccountStatus status;

    private Account (
            Username username,
            Email email,
            PasswordHash password,
            AuthProvider authProvider,
            ProviderId providerId
    ){
        super(AccountId.generate());
        this.email = email;
        this.username= username;
        this.password = password;
        this.authProvider = authProvider;
        this.status = AccountStatus.ACTIVE;
        this.providerId = providerId;
        validateInvariant();
    }

    private Account (
            AccountId id,
            Username username,
            Email email,
            PasswordHash password,
            AuthProvider authProvider,
            ProviderId providerId,
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
        this.providerId = providerId;
        this.status = status;
    }

    public static Account createByUsername(
            Username username,
            PasswordHash password
    ) {
        return new Account(username, null, password, AuthProvider.USERNAME,null);
    }

    public static Account createByEmail(
            Email email,
            PasswordHash password,
            Role role
    ) {
        if(!Objects.equals(role.getName().value(), "customer")){
            throw new InvalidCustomerRoleException();
        }
        Account account = new Account(null, email, password, AuthProvider.EMAIL, null);
        account.grantRole(role);
        return account;
    }

    public static Account createByGoogle(Email email,Role role, ProviderId providerId) {
        if(!Objects.equals(role.getName().value(), "customer")){
            throw new InvalidCustomerRoleException();
        }
        Account account = new Account(null, email, null, AuthProvider.GOOGLE, providerId);
        account.grantRole(role);
        return account;
    }

    public void changePassword(PasswordHash newPassword) {
        checkUpdate();
        if (authProvider == AuthProvider.GOOGLE) {
            throw new InvalidCustomerRoleException();
        }
        this.password = newPassword;
        touch();
    }

    public void changeStatus(AccountStatus newStatus) {
        checkUpdate();
        this.status = newStatus;
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
            ProviderId providerId,
            AccountStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Set<Role> roleList,
            Set<PermissionOverride> permissionOverrides
    ) {
        Account acc = new Account(id,
                username,
                email,
                password,
                authProvider,
                providerId,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
        acc.roleList.addAll(roleList);
        acc.permissionOverrides.addAll(permissionOverrides);
        return acc;
    }

    public void checkUpdate() {
        if(this.username != null && "admin".equals(this.username.value())){
            throw new UserNotDeleteOrUpdateException();
        }
    }

    public void grantRole(Role role) {
        checkUpdate();
        if(RoleStatus.INACTIVE.equals(role.getStatus())){
            throw new RoleInActiveException();
        }
        this.roleList.add(role);
    }

    public void revokeRole(Role role) {
        checkUpdate();
        this.roleList.remove(role);
    }

    public void grantPermissionOverride(PermissionOverride permission) {
        checkUpdate();
        Set<Permission> rolePermissions = new HashSet<>();
        for (Role role : roleList) {
            rolePermissions.addAll(role.getPermissionList());
        }
        if(permission.getPermissionType().equals(PermissionType.DENY)
        && !rolePermissions.contains(permission.getPermission())){
            return;
        }
        if(permission.getPermissionType().equals(PermissionType.GRANT)
                && rolePermissions.contains(permission.getPermission())){
            return;
        }

        Optional<PermissionOverride> existing = this.permissionOverrides.stream()
                .filter(p -> p.getPermission().code().equals(permission.getPermission().code()))
                .findFirst();
        existing.ifPresent(this.permissionOverrides::remove);
        this.permissionOverrides.add(permission);
    }

    public void revokePermissionOverride(PermissionOverride permission) {
        checkUpdate();
        this.permissionOverrides.remove(permission);
    }

    public void syncPermissionOverride() {
        checkUpdate();
        Set<Permission> rolePermissions = new HashSet<>();
        for (Role role : roleList) {
            rolePermissions.addAll(role.getPermissionList());
        }
        Set<PermissionOverride> asyncPer = new HashSet<>(this.permissionOverrides);
        for (PermissionOverride item : asyncPer) {
            if(item.getPermissionType().equals(PermissionType.DENY)
                    && !rolePermissions.contains(item.getPermission())
            ){
                this.permissionOverrides.remove(item);
            }else if(item.getPermissionType().equals(PermissionType.GRANT)
                    && rolePermissions.contains(item.getPermission())
            ){
                this.permissionOverrides.remove(item);
            }
        }
    }

    public Set<Permission> getEffectivePermissions() {
        Set<Permission> effectivePermissions = new HashSet<>();
        for (Role role : roleList) {
            if(RoleStatus.INACTIVE.equals(role.getStatus())){
                continue;
            }
            effectivePermissions.addAll(role.getPermissionList());
        }

        for (PermissionOverride override : permissionOverrides) {
            if (override.getPermissionType() == PermissionType.GRANT) {
                effectivePermissions.add(override.getPermission());
            } else if (override.getPermissionType() == PermissionType.DENY) {
                effectivePermissions.remove(override.getPermission());
            }
        }
        return effectivePermissions;
    }
}

