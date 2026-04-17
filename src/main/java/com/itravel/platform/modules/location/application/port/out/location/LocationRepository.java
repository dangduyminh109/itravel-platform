package com.itravel.platform.modules.location.application.port.out.location;

import com.itravel.platform.modules.location.domain.location.Location;
import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.location.domain.location.LocationName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository {
    Optional<Location> findById(LocationId id,boolean withChildren,boolean withParent, Integer level);
    boolean existsByName(LocationName name);
    boolean existsByNameAndIdNot(LocationName name, LocationId id);
    void save(Location location);
    void destroy(LocationId id);
    void updateStatus(Location location);
    void restore(Location location);
    void delete(Location location);
    Location update(Location location);
}
