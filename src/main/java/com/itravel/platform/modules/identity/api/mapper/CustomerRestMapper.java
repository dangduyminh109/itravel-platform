package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.CustomerCreateRequest;
import com.itravel.platform.modules.identity.api.dto.request.RegisterCustomerByEmailRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateCustomerRequest;
import com.itravel.platform.modules.identity.api.dto.response.CustomerGeneralInfoResponse;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.application.command.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.command.customer.*;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.application.query.CustomerGeneralInfo;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                RoleRestMapper.class,
                IdentityValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerRestMapper {
    CustomerGeneralInfoResponse toCustomerGeneralInfoResponse(CustomerGeneralInfo info);
    CustomerResponse toCustomerResponse(CustomerDetail customer);
    CustomerCreateCommand toCustomerCreateCommand(CustomerCreateRequest request);
    RegisterCustomerByEmailCommand toCreateCustomerByEmailCommand(RegisterCustomerByEmailRequest request);
    UpdateCustomerCommand toUpdateCustomerCommand(String id, UpdateCustomerRequest request);
    DeleteCustomerCommand toDeleteCustomerCommand(String id);
    RestoreCustomerCommand toRestoreCustomerCommand(String id);
}