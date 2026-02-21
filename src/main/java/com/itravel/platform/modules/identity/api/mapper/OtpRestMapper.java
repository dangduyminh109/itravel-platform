package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.SendOtpRequest;
import com.itravel.platform.modules.identity.application.command.auth.SendOtpCommand;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = IdentityValueObjectMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OtpRestMapper {
    SendOtpCommand toSendOtpCommand(SendOtpRequest request);
}