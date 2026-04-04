package com.linkhub.linkhub.user.api;

import com.linkhub.linkhub.modes.application.GetUserModeUseCase;
import com.linkhub.linkhub.user.api.dto.CreateUserRequest;
import com.linkhub.linkhub.user.api.dto.CreateUserResponse;
import com.linkhub.linkhub.user.api.dto.UserResponse;
import com.linkhub.linkhub.user.application.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResult result = createUserUseCase.create(new CreateUserCommand(
                request.username(), request.displayName())
        );
        return new CreateUserResponse(
                result.id(),
                result.username(),
                result.displayName(),
                result.createdAt());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById (@PathVariable Long userId) {
        GetUserResult result = getUserByIdUseCase.getById(userId);
        return ResponseEntity.ok(new UserResponse(
                result.username(),
                result.displayName(),
                result.createdAt()
        ));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername (@PathVariable String username) {
        GetUserResult result = getUserByUsernameUseCase.getByUsername(username);
        return ResponseEntity.ok(new UserResponse(
                result.username(),
                result.displayName(),
                result.createdAt()
        ));
    }
}
