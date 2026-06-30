package ru.itis.dis403.questplatform.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.dis403.questplatform.model.User;
import ru.itis.dis403.questplatform.service.PlayService;
import ru.itis.dis403.questplatform.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/play")
@RequiredArgsConstructor
public class PlayRestController {
    private final PlayService playService;
    private final UserService userService;

    @PostMapping("/check")
    public Map<String, Boolean> checkAnswer(@RequestBody Map<String, Long> body) {
        Long answerId = body.get("answerId");
        boolean isCorrect = playService.checkAnswer(answerId);
        return Map.of("correct", isCorrect);
    }

    @PostMapping("/finish")
    public Map<String, String> finishQuest(Authentication authentication, @RequestBody Map<String, Object> body) {

        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Long questId = Long.valueOf(body.get("questId").toString());
        int score = Integer.parseInt(body.get("score").toString());

        playService.completeQuest(user.getId(), questId, score);
        return Map.of("status", "success", "message", "Квест пройден! Письмо отправлено на почту.");
    }
}