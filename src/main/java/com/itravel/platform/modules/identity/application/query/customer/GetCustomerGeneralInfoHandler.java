package com.itravel.platform.modules.identity.application.query.customer;

import com.itravel.platform.modules.identity.application.dto.CustomerGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCustomerGeneralInfoHandler {
    CustomerQueryPort customerQueryPort;

    public CustomerGeneralInfoDTO getCustomerGeneralInfoDTO() {
        return customerQueryPort.getCustomerGeneralInfoDTO();
    }
}
