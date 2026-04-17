package com.itravel.platform.modules.location.application.port.in.location;

import com.itravel.platform.modules.location.application.command.location.RestoreLocationCommand;

public interface RestoreLocationUseCase {
    void execute(RestoreLocationCommand command);
}
