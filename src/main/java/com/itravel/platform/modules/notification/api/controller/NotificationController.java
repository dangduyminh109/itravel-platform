package com.itravel.platform.modules.notification.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.common.utils.SecurityUtils;
import com.itravel.platform.modules.notification.api.dto.response.NotificationResponse;
import com.itravel.platform.modules.notification.api.mapper.NotificationRestMapper;
import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import com.itravel.platform.modules.notification.application.port.in.notification.facade.NotificationCommandFacade;
import com.itravel.platform.modules.notification.application.port.in.notification.facade.NotificationQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    NotificationCommandFacade notificationCommandFacade;
    NotificationQueryFacade notificationQueryFacade;
    NotificationRestMapper notificationRestMapper;

    @GetMapping
    public ApiResponse<PageResponse<NotificationResponse>> getMyNotifications(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        String accountId = SecurityUtils.getCurrentAccountId();
        Page<NotificationDTO> notificationPage = notificationQueryFacade.getNotificationsByRecipient(accountId, pageable);
        
        PageResponse<NotificationResponse> response = PageResponse.<NotificationResponse>builder()
                .currentPage(notificationPage.getNumber())
                .pageSize(notificationPage.getSize())
                .totalElements(notificationPage.getTotalElements())
                .totalPages(notificationPage.getTotalPages())
                .data(notificationPage.getContent().stream().map(notificationRestMapper::toResponse).toList())
                .build();

        return ApiResponse.<PageResponse<NotificationResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @GetMapping("/unread-count")
    public ApiResponse<Long> getUnreadCount() {
        String accountId = SecurityUtils.getCurrentAccountId();
        return ApiResponse.<Long>builder()
                .success(true)
                .response(notificationQueryFacade.getUnreadCount(accountId))
                .build();
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<Void> markAsRead(@PathVariable String id) {
        notificationCommandFacade.markAsRead(id);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Notification marked as read")
                .build();
    }

    @PatchMapping("/read-all")
    public ApiResponse<Void> markAllAsRead() {
        String accountId = SecurityUtils.getCurrentAccountId();
        notificationCommandFacade.markAllAsRead(accountId);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("All notifications marked as read")
                .build();
    }
}
