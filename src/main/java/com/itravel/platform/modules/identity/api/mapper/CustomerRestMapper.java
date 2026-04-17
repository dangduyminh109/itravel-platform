package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.api.dto.request.CustomerCreateRequest;
import com.itravel.platform.modules.identity.api.dto.request.RegisterCustomerByEmailRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateCustomerRequest;
import com.itravel.platform.modules.identity.api.dto.response.CustomerGeneralInfoResponse;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.application.command.model.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.*;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerGeneralInfoDTO;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                RoleRestMapper.class,
                IdentityValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerRestMapper {
    CustomerGeneralInfoResponse toCustomerGeneralInfoResponse(CustomerGeneralInfoDTO info);
    CustomerResponse toCustomerResponse(CustomerDetailDTO customer);
    List<CustomerResponse> toCustomerResponseList(List<CustomerDetailDTO> customers);

    default PageResponse<CustomerResponse> toCustomerPageResponse(PageResponse<CustomerDetailDTO> page) {
        if (page == null) return null;
        return PageResponse.<CustomerResponse>builder()
                .currentPage(page.getCurrentPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .data(toCustomerResponseList(page.getData()))
                .build();
    }
    CustomerCreateCommand toCustomerCreateCommand(CustomerCreateRequest request);
    RegisterCustomerByEmailCommand toCreateCustomerByEmailCommand(RegisterCustomerByEmailRequest request);
    UpdateCustomerCommand toUpdateCustomerCommand(String id, UpdateCustomerRequest request);
    DeleteCustomerCommand toDeleteCustomerCommand(String id);
    RestoreCustomerCommand toRestoreCustomerCommand(String id);
}