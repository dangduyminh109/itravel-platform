package com.itravel.platform.modules.tour.application.port.in.category.facade;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.query.category.GetAllCategoryHandler;
import com.itravel.platform.modules.tour.application.query.category.GetCategoryHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryQueryFacade {
    GetAllCategoryHandler getAllHandler;
    GetCategoryHandler getHandler;

    public Page<CategoryDetailDTO> getAll(String keyword, Pageable pageable, Boolean isDeleted, String status) {
        return getAllHandler.getAll(keyword,pageable, isDeleted, status);
    }

    public CategoryDetailDTO getById(Long id) {
        return getHandler.getById(id);
    }
}