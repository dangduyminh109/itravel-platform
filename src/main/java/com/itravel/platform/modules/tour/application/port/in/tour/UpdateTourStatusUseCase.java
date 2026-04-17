package com.itravel.platform.modules.tour.application.port.in.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.UpdateTourStatusCommand;

public interface UpdateTourStatusUseCase {
    void execute(UpdateTourStatusCommand command);
}

