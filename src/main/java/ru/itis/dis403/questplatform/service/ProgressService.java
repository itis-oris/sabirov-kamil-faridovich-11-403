package ru.itis.dis403.questplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dis403.questplatform.converter.ProgressToProgressDTOConverter;
import ru.itis.dis403.questplatform.dto.UserQuestProgressDTO;
import ru.itis.dis403.questplatform.repository.UserQuestProgressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final UserQuestProgressRepository progressRepository;
    private final ProgressToProgressDTOConverter progressConverter;

    public List<UserQuestProgressDTO> getUserHistory(Long userId) {
        return progressRepository.findByUserId(userId).stream()
                .map(progressConverter::convert)
                .sorted((a, b) -> {
                    if (a.getFinishedAt() == null && b.getFinishedAt() == null) return 0;
                    if (a.getFinishedAt() == null) return 1;
                    if (b.getFinishedAt() == null) return -1;
                    return b.getFinishedAt().compareTo(a.getFinishedAt());
                })
                .collect(Collectors.toList());
    }
}