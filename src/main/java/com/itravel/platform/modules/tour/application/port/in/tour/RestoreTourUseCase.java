package com.itravel.platform.modules.tour.application.port.in.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.RestoreTourCommand;

public interface RestoreTourUseCase {
    void execute(RestoreTourCommand command);
}