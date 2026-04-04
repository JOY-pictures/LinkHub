package com.linkhub.linkhub.modes.api;

import com.linkhub.linkhub.modes.api.dto.UserModeRequest;
import com.linkhub.linkhub.modes.api.dto.UserModeResponse;
import com.linkhub.linkhub.modes.application.SetModeCommand;
import com.linkhub.linkhub.modes.application.SetModeResult;
import com.linkhub.linkhub.modes.application.GetUserModeUseCase;
import com.linkhub.linkhub.modes.application.SetUserModeUseCase;
import com.linkhub.linkhub.modes.domain.Mode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/mode")
public class UserModeController {

    private final SetUserModeUseCase setUserModeUseCase;
    private final GetUserModeUseCase getUserModeUseCase;

    @PostMapping
    public ResponseEntity<UserModeResponse> setMode(@PathVariable Long userId,
                                                    @Valid @RequestBody UserModeRequest request
    ) {
        SetModeResult response = setUserModeUseCase.setMode(new SetModeCommand(userId, request.modeName()));
        return ResponseEntity.ok(new UserModeResponse(response.userId(), response.modeName()));
    }

    @GetMapping
    public ResponseEntity<UserModeResponse> getMode(@PathVariable Long userId
    ) {
        Mode mode = getUserModeUseCase.getMode(userId);
        return ResponseEntity.ok(new UserModeResponse(userId, mode.getName()));
    }
}
