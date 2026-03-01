package com.itravel.platform.common.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class PageResponse<T> {
    Integer currentPage;
    Integer pageSize;
    Integer totalPages;
    Long totalElements;
    List<T> data;
}
