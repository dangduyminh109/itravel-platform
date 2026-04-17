package com.itravel.platform.modules.location.application.port.in.location;

import com.itravel.platform.modules.location.application.command.location.CreateLocationCommand;
import com.itravel.platform.modules.location.domain.location.Location;

public interface CreateLocationUseCase {
    Location execute(CreateLocationCommand command);
}
