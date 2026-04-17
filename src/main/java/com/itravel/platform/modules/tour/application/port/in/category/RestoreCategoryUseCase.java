package com.itravel.platform.modules.tour.application.port.in.category;

import com.itravel.platform.modules.tour.application.command.model.category.RestoreCategoryCommand;

public interface RestoreCategoryUseCase {
    void execute(RestoreCategoryCommand command);
}
