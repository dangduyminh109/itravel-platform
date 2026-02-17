package com.itravel.platform.modules.identity.application.command.customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;

public record DeleteCustomerCommand(CustomerId id) {
}
