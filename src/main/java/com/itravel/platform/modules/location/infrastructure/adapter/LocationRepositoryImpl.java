package com.itravel.platform.modules.location.infrastructure.adapter;

import com.itravel.platform.modules.location.application.port.out.location.LocationRepository;
import com.itravel.platform.modules.location.domain.location.Location;
import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.location.domain.location.LocationName;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.location.infrastructure.persistence.mapper.LocationMapper;
import com.itravel.platform.modules.location.infrastructure.persistence.repository.LocationJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationRepositoryImpl implements LocationRepository {
    LocationJpaRepository locationJpaRepository;
    LocationMapper mapper;

    @Override
    public Optional<Location> findById(LocationId id, boolean withChildren, boolean withParent, Integer level) {
        LocationJpaEntity entity = locationJpaRepository.findById(id.value())
                .orElse(null);
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(LocationMapper.toLocationDomain(entity, withChildren, withParent, level));
    }

    @Override
    public boolean existsByName(LocationName name) {
        return locationJpaRepository.existsByName(name.value());
    }

    @Override
    public boolean existsByNameAndIdNot(LocationName name, LocationId id) {
        return locationJpaRepository.existsByNameAndIdNot(name.value(), id.value());
    }

    @Override
    public void save(Location location) {
        LocationJpaEntity locationJpaEntity = mapper.toLocationJpaEntity(location);
        locationJpaRepository.save(locationJpaEntity);
    }

    @Override
    public Location update(Location location) {
        Optional<LocationJpaEntity> entity =  locationJpaRepository.findById(location.getId().value());
        if(entity.isPresent()){
            LocationJpaEntity locationJpaEntity = entity.get();
            locationJpaEntity.setName(location.getName().value());
            locationJpaEntity.setSlug(location.getSlug().value());
            locationJpaEntity.setType(location.getType());
            locationJpaEntity.setStatus(location.getStatus());
            locationJpaEntity.setUpdatedAt(location.getUpdatedAt());
            if(location.getParent() != null && locationJpaEntity.getParent() != null){
                if(!location.getParent().getId().value().equals(locationJpaEntity.getParent().getId())){
                    Optional<LocationJpaEntity> newParent =  locationJpaRepository.findById(location.getParent().getId().value());
                    newParent.ifPresent(locationJpaEntity::setParent);
                }
            }else {
                locationJpaEntity.setParent(null);
            }
            locationJpaRepository.save(locationJpaEntity);
            return LocationMapper.toLocationDomain(locationJpaEntity, true,true, 1);
        }
        return null;
    }

    @Override
    public void destroy(LocationId id) {
        Optional<LocationJpaEntity> locationOpt= locationJpaRepository.findById(id.value());
        if(locationOpt.isEmpty()){
            return;
        }
        LocationJpaEntity location =  locationOpt.get();
        List<LocationJpaEntity> children  = location.getChildren();
        for(LocationJpaEntity child:children) {
            child.setParent(location.getParent());
        }
        locationJpaRepository.saveAll(children);
        location.getChildren().clear();
        locationJpaRepository.delete(location);
    }

    @Override
    public void updateStatus(Location location) {
        locationJpaRepository.findById(location.getId().value())
                .ifPresent((entity) -> {
                    entity.setStatus(location.getStatus());
                    entity.setUpdatedAt(location.getUpdatedAt());
                    locationJpaRepository.save(entity);
                });
    }

    @Override
    public void restore(Location location) {
        locationJpaRepository.findById(location.getId().value())
                .ifPresent((entity) -> {
                    entity.setDeletedAt(null);
                    entity.setUpdatedAt(location.getUpdatedAt());
                    locationJpaRepository.save(entity);
                });
    }

    @Override
    public void delete(Location location) {
        locationJpaRepository.findById(location.getId().value())
                .ifPresent((entity) -> {
                    entity.setDeletedAt(location.getDeletedAt());
                    locationJpaRepository.save(entity);
                });
    }
}
