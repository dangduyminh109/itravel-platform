package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.api.dto.request.CustomerCreateRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateCustomerRequest;
import com.itravel.platform.modules.identity.api.dto.response.CustomerGeneralInfoResponse;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.api.mapper.CustomerRestMapper;
import com.itravel.platform.modules.identity.application.command.customer.CustomerCreateCommand;
import com.itravel.platform.modules.identity.application.command.customer.UpdateCustomerCommand;
import com.itravel.platform.modules.identity.application.handler.AccountCommandHandler;
import com.itravel.platform.modules.identity.application.handler.CustomerCommandHandler;
import com.itravel.platform.modules.identity.application.service.CustomerQueryService;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/customer")
public class CustomerController {
    CustomerCommandHandler customerCommandHandler;
    CustomerQueryService customerQueryService;
    CustomerRestMapper mapper;
    AccountCommandHandler accountCommandHandler;

    @GetMapping("/general-info")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ApiResponse<CustomerGeneralInfoResponse> getCustomerGeneralInfo() {
        return ApiResponse.<CustomerGeneralInfoResponse>builder()
                .success(true)
                .response(mapper.toCustomerGeneralInfoResponse(customerQueryService.getCustomerGeneralInfo()))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ApiResponse<CustomerResponse> getCustomer(
            @PathVariable String id
    ) {
        return ApiResponse.<CustomerResponse>builder()
                .success(true)
                .response(customerQueryService.getCustomer(new CustomerId(id)))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ApiResponse<PageResponse<CustomerResponse>> getCustomers(
            @RequestParam (required = false) String keyword,
            @RequestParam (required = false, defaultValue = "false") boolean isDeleted,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        var response = customerQueryService.getCustomers(keyword,pageable,isDeleted);
        return ApiResponse.<PageResponse<CustomerResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<CustomerResponse> getMe() {
        return ApiResponse.<CustomerResponse>builder()
                .success(true)
                .response(mapper.toCustomerResponse(customerQueryService.getMe()))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
    public ApiResponse<CustomerResponse> create(@ModelAttribute @Valid CustomerCreateRequest request) {
        CustomerCreateCommand command = mapper.toCustomerCreateCommand(request);
        return ApiResponse.<CustomerResponse>builder()
                .message("Create customer successfully")
                .success(true)
                .response(mapper
                        .toCustomerResponse(customerCommandHandler
                                .create(command)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    public ApiResponse<CustomerResponse> update(@PathVariable String id, @ModelAttribute @Valid UpdateCustomerRequest request) {
        UpdateCustomerCommand command = mapper.toUpdateCustomerCommand(id,request);
        return ApiResponse.<CustomerResponse>builder()
                .message("Update customer successfully")
                .success(true)
                .response(mapper.toCustomerResponse(customerCommandHandler.update(command)))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        accountCommandHandler.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete customer successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable String id) {
        customerCommandHandler.restore(mapper.toRestoreCustomerCommand(id));
        return ApiResponse.<Void>builder()
                .message("Restore customer successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        customerCommandHandler.destroy(mapper.toDeleteCustomerCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy customer successfully")
                .success(true)
                .build();
    }
}
