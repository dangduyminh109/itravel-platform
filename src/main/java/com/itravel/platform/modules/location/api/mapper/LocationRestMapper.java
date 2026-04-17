package com.itravel.platform.modules.location.api.mapper;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.api.dto.request.CreateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateStatusLocationRequest;
import com.itravel.platform.modules.location.api.dto.response.LocationResponse;
import com.itravel.platform.modules.location.application.command.location.*;
import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.domain.location.Location;
import com.itravel.platform.modules.location.share.LocationValueObjectMapper;
import com.itravel.platform.modules.tour.share.CommonValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {LocationValueObjectMapper.class, CommonValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LocationRestMapper {
        LocationResponse toLocationResponse(Location location);

        LocationResponse toLocationResponse(LocationDetailDTO dto);

        LocationListItemDTO toLocationResponse(LocationListItemDTO dto);

        default PageResponse<LocationListItemDTO> toPageResponse(PageResponse<LocationListItemDTO> pageDTO) {
                return PageResponse.<LocationListItemDTO>builder()
                                .currentPage(pageDTO.getCurrentPage())
                                .pageSize(pageDTO.getPageSize())
                                .totalElements(pageDTO.getTotalElements())
                                .totalPages(pageDTO.getTotalPages())
                                .data(pageDTO.getData().stream().map(this::toLocationResponse).toList())
                                .build();
        }

        CreateLocationCommand toCreateLocationCommand(CreateLocationRequest request);

        UpdateLocationCommand toUpdateLocationCommand(String id, UpdateLocationRequest request);

        DeleteLocationCommand toDeleteLocationCommand(String id);

        RestoreLocationCommand toRestoreLocationCommand(String id);

        UpdateStatusLocationCommand toUpdateStatusLocationCommand(String id, UpdateStatusLocationRequest request);
}