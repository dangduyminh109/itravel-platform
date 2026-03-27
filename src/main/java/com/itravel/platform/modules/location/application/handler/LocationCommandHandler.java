package com.itravel.platform.modules.location.application.handler;

import com.itravel.platform.modules.location.application.command.location.*;
import com.itravel.platform.modules.location.application.exception.LocationNameExistedException;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.domain.aggregate.Location;
import com.itravel.platform.modules.location.domain.repository.LocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationCommandHandler {
    LocationRepository locationRepository;

    @Transactional
    public Location create(CreateLocationCommand command) {
        Location parent = null;
        if(command.parentId() != null){
             parent = locationRepository.findById(command.parentId(),false,false,0)
                    .orElseThrow(LocationNotFoundException::new);
        }

        if(locationRepository.existsByName(command.name())){
            throw new LocationNameExistedException();
        }

        Location location = Location.create(
                command.name(),
                command.type(),
                parent,
                command.status()
        );
        locationRepository.save(location);
        return location;
    }

    @Transactional
    public Location update(UpdateLocationCommand command){
        Location location = locationRepository.findById(command.id(),false,true,0)
                .orElseThrow(LocationNotFoundException::new);
        location.updateName(command.name());
        location.updateStatus(command.status());
        location.updateType(command.type());

        if(locationRepository.existsByNameAndIdNot(command.name(), location.getId())){
            throw new LocationNameExistedException();
        }

        if(command.parentId() != null){
            Location parent = locationRepository.findById(command.parentId(),false,false,0)
                    .orElseThrow(LocationNotFoundException::new);
            location.updatePrent(parent);
        }else {
            location.updatePrent(null);
        }

        Location updated= locationRepository.update(location);
        if(updated == null){
            throw new LocationNotFoundException();
        }
        return updated;
    }

    @Transactional
    public void delete(DeleteLocationCommand command){
        Location location = locationRepository.findById(command.id() ,false,true,1)
                        .orElseThrow(LocationNotFoundException::new);
        location.softDelete();
        locationRepository.delete(location);
    }


    @Transactional
    public void status(UpdateStatusLocationCommand command){
        Location location = locationRepository.findById(command.id() ,false,false,0)
                .orElseThrow(LocationNotFoundException::new);
        location.updateStatus(command.status());
        locationRepository.updateStatus(location);
    }

    @Transactional
    public void restore(RestoreLocationCommand command){
        Location location = locationRepository.findById(command.id() ,false,false,0)
                        .orElseThrow(LocationNotFoundException::new);
        location.restore();
        locationRepository.restore(location);
    }

    @Transactional
    public void destroy(DeleteLocationCommand command){
        locationRepository.destroy(command.id());
    }
}
