package com.itravel.platform.modules.payment.api.mapper;

import com.itravel.platform.modules.payment.api.dto.CreatePaymentRequest;
import com.itravel.platform.modules.payment.application.command.model.CreatePaymentCommand;
import com.itravel.platform.modules.payment.share.PaymentValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {PaymentValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PaymentRestMapper {
    CreatePaymentCommand toCommand(CreatePaymentRequest request, String clientId);
}
