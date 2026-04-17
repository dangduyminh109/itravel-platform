package com.itravel.platform.modules.identity.application.query.customer;

import com.itravel.platform.common.utils.SecurityUtils;
import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerQueryPort;
import com.itravel.platform.modules.identity.application.query.accountlink.GetAccountLinkHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCustomerMeHandler {
    CustomerQueryPort customerQueryPort;
    GetAccountLinkHandler getAccountLinkHandler;

    public CustomerDetailDTO getMe() {
        String accountId = SecurityUtils.getCurrentAccountId();
        AccountLinkDTO link = getAccountLinkHandler.getByAccountId(accountId);
        return customerQueryPort.getCustomer(link.targetId())
                .orElseThrow(CustomerNotExistException::new);
    }
}
