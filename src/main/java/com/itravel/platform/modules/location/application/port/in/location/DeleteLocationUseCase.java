package com.itravel.platform.modules.location.application.port.in.location;

import com.itravel.platform.modules.location.application.command.location.DeleteLocationCommand;

public interface DeleteLocationUseCase {
    void execute(DeleteLocationCommand command);
}
