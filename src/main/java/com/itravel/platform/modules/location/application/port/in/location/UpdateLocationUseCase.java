package com.itravel.platform.modules.location.application.port.in.location;

import com.itravel.platform.modules.location.application.command.location.UpdateLocationCommand;
import com.itravel.platform.modules.location.domain.location.Location;

public interface UpdateLocationUseCase {
    Location execute(UpdateLocationCommand command);
}
