package com.linkhub.linkhub.modes.api;

import com.linkhub.linkhub.modes.application.SetUserModeUseCase;
import com.linkhub.linkhub.modes.domain.Mode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/mode")
public class ModeController {

    private final SetUserModeUseCase setUserModeUseCase;

    @PostMapping
    public ResponseEntity<String> setMode(@RequestBody ModeRequest request) {
        setUserModeUseCase.setMode(request.userId(), request.modeName());
        return ResponseEntity.ok("Mode set to: " + request.modeName());
    }

    @GetMapping
    public ResponseEntity<String> getMode() {
        return ResponseEntity.ok("Current mode: Learning");
    }

    public record ModeRequest(
            String userId,
            String modeName
    ) {}


}
