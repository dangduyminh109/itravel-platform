package com.itravel.platform.modules.location.infrastructure.adapter;

import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.dto.LocationGeneralInfoDTO;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.application.port.out.location.LocationQueryPort;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.location.infrastructure.persistence.mapper.LocationMapper;
import com.itravel.platform.modules.location.infrastructure.persistence.repository.LocationJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationQueryAdapter implements LocationQueryPort {
    LocationJpaRepository locationJpaRepository;

    @Override
    public List<LocationDetailDTO> getTree(Boolean isDeleted, LocationStatus status) {
        List<LocationJpaEntity> locationTree = locationJpaRepository.getTree(isDeleted, status);
        Map<Long, LocationDetailDTO> map = new HashMap<>();
        List<LocationDetailDTO> roots = new ArrayList<>();

        for (LocationJpaEntity entity : locationTree) {
            LocationDetailDTO location = LocationMapper.toDetailDTO(entity, false, false, 0);
            map.put(entity.getId(), location);
        }

        for (LocationJpaEntity entity : locationTree) {
            if (entity.getParent() == null) {
                LocationDetailDTO root = map.get(entity.getId());
                if (root != null) {
                    roots.add(root);
                }
            } else {
                LocationDetailDTO parent = map.get(entity.getParent().getId());
                if (parent != null) {
                    parent.getChildren().add(map.get(entity.getId()));
                }
            }
        }
        return roots;
    }

    @Override
    public Page<LocationListItemDTO> getLocations(String keyword, Pageable pageable, Boolean isDeleted,
            LocationStatus status) {
        Page<LocationJpaEntity> locationJpaList = locationJpaRepository.getLocations(pageable, keyword, isDeleted,
                status);
        Map<Long, LocationListItemDTO> mapOriginal = new HashMap<>();
        Map<Long, LocationListItemDTO> mapLocation = new HashMap<>();

        for (LocationJpaEntity entity : locationJpaList) {
            LocationListItemDTO origin = LocationMapper.toListItemDTO(entity, false, 0);
            LocationListItemDTO location = LocationMapper.toListItemDTO(entity, false, 0);
            mapOriginal.put(entity.getId(), origin);
            mapLocation.put(entity.getId(), location);
        }

        for (LocationJpaEntity entity : locationJpaList) {
            LocationListItemDTO location = mapLocation.get(entity.getId());
            if (entity.getParent() != null) {
                LocationListItemDTO parent = mapLocation.get(entity.getParent().getId());
                if (parent != null) {
                    location.setParent(mapOriginal.get(entity.getParent().getId()));
                }
            }
        }

        return locationJpaList.map(entity -> mapLocation.get(entity.getId()));
    }

    @Override
    public Optional<LocationDetailDTO> findById(Long id, boolean withChildren, boolean withParent, Integer level) {
        return locationJpaRepository.findById(id)
                .map(entity -> LocationMapper.toDetailDTO(entity, withChildren, withParent, level));
    }

    @Override
    public LocationGeneralInfoDTO getLocationGeneralInfoDTO() {
        return locationJpaRepository.getLocationGeneralInfoDTO();
    }
}
