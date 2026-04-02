package com.itravel.platform.modules.tour.application.query.category;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAllCategoryHandler {
    CategoryQueryPort queryPort;

    public Page<CategoryDetailDTO> getAll(
            String keyword, Pageable pageable, Boolean isDeleted, String status
    ) {
        return queryPort.getCategories(keyword,pageable, isDeleted, status);
    }
}
