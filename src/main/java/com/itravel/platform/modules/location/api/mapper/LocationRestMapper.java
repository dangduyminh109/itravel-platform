package com.itravel.platform.modules.location.api.mapper;

import com.itravel.platform.modules.location.api.dto.request.CreateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateStatusLocationRequest;
import com.itravel.platform.modules.location.api.dto.response.LocationResponse;
import com.itravel.platform.modules.location.application.command.location.*;
import com.itravel.platform.modules.location.domain.aggregate.Location;
import com.itravel.platform.modules.location.share.LocationValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                LocationValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LocationRestMapper {
    LocationResponse toLocationResponse(Location location);
    CreateLocationCommand toCreateLocationCommand(CreateLocationRequest request);
    UpdateLocationCommand toUpdateLocationCommand(String id, UpdateLocationRequest request);
    DeleteLocationCommand toDeleteLocationCommand(String id);
    RestoreLocationCommand toRestoreLocationCommand(String id);
    UpdateStatusLocationCommand toUpdateStatusLocationCommand(String id,UpdateStatusLocationRequest request);
}