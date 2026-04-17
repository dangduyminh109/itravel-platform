package com.itravel.platform.modules.identity.application.command.model.customer;

import com.itravel.platform.modules.identity.domain.customer.CustomerId;

public record ChangeCustomerStatusCommand(CustomerId id) {
}

