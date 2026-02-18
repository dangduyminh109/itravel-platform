package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.RegisterCustomerByEmailRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateCustomerRequest;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.application.command.customer.DeleteCustomerCommand;
import com.itravel.platform.modules.identity.application.command.customer.RestoreCustomerCommand;
import com.itravel.platform.modules.identity.application.command.customer.UpdateCustomerCommand;
import com.itravel.platform.modules.identity.application.command.customer.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = RoleRestMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerRestMapper {
    @Mapping(target = "id",
            expression = "java(customer.id().value() != null ? customer.id().value() : null)")
    @Mapping(target = "fullName",
            expression = "java(customer.fullName().value() != null ? customer.fullName().value() : null)")
    @Mapping(target = "email",
            expression = "java(customer.email().value() != null ? customer.email().value() : null)")
    CustomerResponse toCustomerResponse(CustomerDetail customer);

    @Mapping(target = "email",
            expression = "java(request.email() != null ? new Email(request.email()) : null)")
    @Mapping(target = "password",
            expression = "java(request.password() != null ? new PasswordHash(request.password()) : null)")
    @Mapping(target = "fullName",
            expression = "java(request.fullName() != null ? new FullName(request.fullName()) : null)")
    RegisterCustomerByEmailCommand toCreateCustomerByEmailCommand(RegisterCustomerByEmailRequest request);

    @Mapping(target = "id",
            expression = "java(id != null ? new CustomerId(id) : null)")
    @Mapping(target = "fullName",
            expression = "java(request.fullName() != null ? new FullName(request.fullName()) : null)")
    @Mapping(target = "newPassword",
            expression = "java(request.newPassword() != null ? new PasswordHash(request.newPassword()) : null)")
    UpdateCustomerCommand toUpdateCustomerCommand(String id, UpdateCustomerRequest request);

    @Mapping(target = "id",
            expression = "java(id != null ? new CustomerId(id) : null)")
    DeleteCustomerCommand toDeleteCustomerCommand(String id);

    @Mapping(target = "id",
            expression = "java(id != null ? new CustomerId(id) : null)")
    RestoreCustomerCommand toRestoreCustomerCommand(String id);

    default RoleId map(Long roleId) {
        return roleId != null ? new RoleId(roleId) : null;
    }

    default Long map(Role role) {
        return role.getId().value();
    }
 }