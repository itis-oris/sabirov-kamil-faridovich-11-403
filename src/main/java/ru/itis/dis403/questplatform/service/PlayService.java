package ru.itis.dis403.questplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.questplatform.model.*;
import ru.itis.dis403.questplatform.repository.*;
import ru.itis.dis403.questplatform.util.EmailService;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayService {
    private final AnswerRepository answerRepository;
    private final UserQuestProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final EmailService emailService;

    public boolean checkAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Ответ не найден"));
        return answer.isCorrect();
    }

    @Transactional
    public void completeQuest(Long userId, Long questId, int score) {
        log.info("User {} completed quest {} with score {}", userId, questId, score);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Квест не найден"));

        UserQuestProgress progress = new UserQuestProgress();
        progress.setUser(user);
        progress.setQuest(quest);
        progress.setCompleted(true);
        progress.setScore(score);
        progress.setFinishedAt(LocalDateTime.now());

        progressRepository.save(progress);

        try {
            emailService.sendCompletionEmail(user.getEmail(), user.getUsername(), quest.getTitle());
        } catch (Exception e) {
            log.error("Ошибка отправки email", e);
        }
    }
}