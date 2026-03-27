package com.itravel.platform.modules.location.domain.repository;

import com.itravel.platform.modules.location.domain.aggregate.Location;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository {
    Optional<Location> findById(LocationId id,boolean withChildren,boolean withParent, Integer level);
    boolean existsByName(LocationName name);
    boolean existsByNameAndIdNot(LocationName name, LocationId id);
    List<Location> getTree(Boolean isDeleted, LocationStatus status);
    Page<Location> getLocations(String keyword, Pageable pageable, Boolean isDeleted, LocationStatus status);
    void save(Location location);
    void destroy(LocationId id);
    void updateStatus(Location location);
    void restore(Location location);
    void delete(Location location);
    Location update(Location location);
}
