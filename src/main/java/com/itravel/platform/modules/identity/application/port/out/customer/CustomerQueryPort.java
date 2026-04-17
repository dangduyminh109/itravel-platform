package com.itravel.platform.modules.identity.application.port.out.customer;

import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerGeneralInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CustomerQueryPort {
    CustomerGeneralInfoDTO getCustomerGeneralInfoDTO();
    Page<CustomerDetailDTO> getCustomers(String keyword, Pageable pageable, boolean isDeleted);
    Optional<CustomerDetailDTO> getCustomer(String id);
}
