package ru.itis.dis403.questplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.questplatform.form.ReviewForm;
import ru.itis.dis403.questplatform.model.Quest;
import ru.itis.dis403.questplatform.model.Review;
import ru.itis.dis403.questplatform.model.User;
import ru.itis.dis403.questplatform.repository.QuestRepository;
import ru.itis.dis403.questplatform.repository.ReviewRepository;
import ru.itis.dis403.questplatform.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveReview(Long questId, Long userId, ReviewForm form) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Квест не найден"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Review review = new Review();
        review.setQuest(quest);
        review.setUser(user);
        review.setRating(form.getRating());
        review.setContent(form.getContent());

        reviewRepository.save(review);
        log.info("Review saved for quest {} by user {}", questId, userId);
    }

    public List<Review> getReviewsByQuestId(Long questId) {
        return reviewRepository.findByQuestIdOrderByCreatedAtDesc(questId);
    }
}