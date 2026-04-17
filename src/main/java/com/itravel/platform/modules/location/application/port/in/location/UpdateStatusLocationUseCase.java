package com.itravel.platform.modules.location.application.port.in.location;

import com.itravel.platform.modules.location.application.command.location.UpdateStatusLocationCommand;

public interface UpdateStatusLocationUseCase {
    void execute(UpdateStatusLocationCommand command);
}
