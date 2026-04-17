package com.itravel.platform.modules.tour.application.port.out.category;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
public interface CategoryRepository {
    CategoryDetailDTO save(Category category);
    void destroy(CategoryId id);
}
