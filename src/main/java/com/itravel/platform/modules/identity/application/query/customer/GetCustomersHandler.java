package com.itravel.platform.modules.identity.application.query.customer;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCustomersHandler {
        CustomerQueryPort customerQueryPort;

        public PageResponse<CustomerDetailDTO> getCustomers(String keyword, Pageable pageable, boolean isDeleted) {
                Page<CustomerDetailDTO> responsePage = customerQueryPort.getCustomers(keyword, pageable, isDeleted);

                return PageResponse.<CustomerDetailDTO>builder()
                                .currentPage(responsePage.getNumber())
                                .pageSize(responsePage.getSize())
                                .totalPages(responsePage.getTotalPages())
                                .totalElements(responsePage.getTotalElements())
                                .data(responsePage.getContent())
                                .build();
        }
}
