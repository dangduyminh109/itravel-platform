package com.itravel.platform.modules.tour.application.query.category;

import com.itravel.platform.modules.tour.application.dto.CategoryGeneralInfoDTO;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCategoryGeneralInfoHandler {
    CategoryQueryPort categoryQueryPort;

    public CategoryGeneralInfoDTO getCategoryGeneralInfoDTO() {
        return categoryQueryPort.getCategoryGeneralInfoDTO();
    }
}
