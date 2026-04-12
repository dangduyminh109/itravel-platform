package com.itravel.platform.modules.tour.application.port.in.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.DeleteTourCommand;

public interface DeleteTourUseCase {
    void execute(DeleteTourCommand command);
}