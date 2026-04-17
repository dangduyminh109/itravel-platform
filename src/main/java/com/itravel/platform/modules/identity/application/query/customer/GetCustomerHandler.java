package com.itravel.platform.modules.identity.application.query.customer;

import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCustomerHandler {
    CustomerQueryPort customerQueryPort;

    public CustomerDetailDTO getCustomer(String id) {
        return customerQueryPort.getCustomer(id)
                .orElseThrow(CustomerNotExistException::new);
    }
}
