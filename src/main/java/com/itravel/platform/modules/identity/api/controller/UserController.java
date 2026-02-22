package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.CreateUserRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateUserRequest;
import com.itravel.platform.modules.identity.api.dto.response.UserResponse;
import com.itravel.platform.modules.identity.api.mapper.UserRestMapper;
import com.itravel.platform.modules.identity.application.command.user.CreateUserCommand;
import com.itravel.platform.modules.identity.application.command.user.UpdateUserCommand;
import com.itravel.platform.modules.identity.application.handler.UserCommandHandler;
import com.itravel.platform.modules.identity.application.service.UserQueryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/user")
public class UserController {
    UserCommandHandler userCommandHandler;
    UserQueryService userQueryService;
    UserRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .data(
                        userQueryService.getUsers().stream()
                                .map(mapper::toUserResponse)
                                .toList()
                )
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ApiResponse<UserResponse> create(@RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUserCommand createUserCommand = mapper.toCreateUserCommand(createUserRequest);
        return ApiResponse.<UserResponse>builder()
                .message("Create user successfully")
                .success(true)
                .data(mapper.toUserResponse(userCommandHandler.CreateSystemUser(createUserCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ApiResponse<UserResponse> update(@PathVariable String id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        UpdateUserCommand updateUserCommand = mapper.toUpdateUserCommand(id, updateUserRequest);
        return ApiResponse.<UserResponse>builder()
                .message("Update user successfully")
                .success(true)
                .data(mapper.toUserResponse(userCommandHandler.update(updateUserCommand)))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        userCommandHandler.delete(mapper.toDeleteUserCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete user successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable String id) {
        userCommandHandler.restore(mapper.toRestoreUserCommand(id));
        return ApiResponse.<Void>builder()
                .message("Restore user successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        userCommandHandler.destroy(mapper.toDeleteUserCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy user successfully")
                .success(true)
                .build();
    }
}
