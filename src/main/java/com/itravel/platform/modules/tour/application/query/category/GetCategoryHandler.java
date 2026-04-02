package com.itravel.platform.modules.tour.application.query.category;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCategoryHandler {
    CategoryQueryPort queryPort;

    public CategoryDetailDTO getById(Long id) {
        return queryPort.getById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
