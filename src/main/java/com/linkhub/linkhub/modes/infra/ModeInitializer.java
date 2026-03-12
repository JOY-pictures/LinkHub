package com.linkhub.linkhub.modes.infra;

import com.linkhub.linkhub.modes.domain.Mode;
import com.linkhub.linkhub.modes.domain.ModeRepository;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModeInitializer implements CommandLineRunner {

    private final ModeRepository modeRepository;

    @Override
    public void run(String... args) {

        List<String> modes = List.of(
                "Balanced",
                "Learning",
                "Fun",
                "Calm",
                "DeepThinking",
                "Motivation"
        );

        for (String name : modes) {
            modeRepository.findByName(name)
                    .orElseGet(() -> modeRepository.save(new Mode(name)));        }
    }
}
