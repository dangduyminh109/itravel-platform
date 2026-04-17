package com.itravel.platform.modules.identity.application.port.in.customer.facade;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.query.customer.GetCustomerGeneralInfoHandler;
import com.itravel.platform.modules.identity.application.query.customer.GetCustomerHandler;
import com.itravel.platform.modules.identity.application.query.customer.GetCustomerMeHandler;
import com.itravel.platform.modules.identity.application.query.customer.GetCustomersHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerQueryFacade {
    GetCustomerHandler getCustomerHandler;
    GetCustomersHandler getCustomersHandler;
    GetCustomerGeneralInfoHandler getCustomerGeneralInfoHandler;
    GetCustomerMeHandler getCustomerMeHandler;

    public CustomerGeneralInfoDTO getCustomerGeneralInfo() {
        return getCustomerGeneralInfoHandler.getCustomerGeneralInfoDTO();
    }

    public PageResponse<CustomerDetailDTO> getCustomers(String keyword, Pageable pageable, boolean isDeleted) {
        return getCustomersHandler.getCustomers(keyword, pageable, isDeleted);
    }

    public CustomerDetailDTO getCustomer(String id) {
        return getCustomerHandler.getCustomer(id);
    }

    public CustomerDetailDTO getMe() {
        return getCustomerMeHandler.getMe();
    }
}
