package com.linkhub.linkhub.history.api;

import com.linkhub.linkhub.history.api.dto.AddToHistoryRequest;
import com.linkhub.linkhub.history.api.dto.AddToHistoryResponse;
import com.linkhub.linkhub.history.application.dto.AddToHistoryCommand;
import com.linkhub.linkhub.history.application.dto.AddToHistoryResult;
import com.linkhub.linkhub.history.application.usecase.AddToHistoryByViewUseCase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final Clock clock;
    private final AddToHistoryByViewUseCase addToHistoryByViewUseCase;

    @PostMapping
    public AddToHistoryResponse addByView(@Valid @RequestBody AddToHistoryRequest request) {
        Instant now = Instant.now(clock);
        AddToHistoryResult result = addToHistoryByViewUseCase.addByView(new AddToHistoryCommand(
                request.userId(),
                request.postId(),
                now
        ));
        return new AddToHistoryResponse(
                result.userId(),
                result.postId(),
                result.viewedAt()
        );
    }

}
