package ru.itis.dis403.questplatform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.dis403.questplatform.dto.UserQuestProgressDTO;
import ru.itis.dis403.questplatform.model.UserQuestProgress;

import java.time.format.DateTimeFormatter;

@Component
public class ProgressToProgressDTOConverter implements Converter<UserQuestProgress, UserQuestProgressDTO> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public UserQuestProgressDTO convert(UserQuestProgress source) {
        if (source == null) return null;

        UserQuestProgressDTO dto = new UserQuestProgressDTO();
        dto.setId(source.getId());

        if (source.getQuest() != null) {
            dto.setQuestTitle(source.getQuest().getTitle());
        } else {
            dto.setQuestTitle("Квест удалён");
        }

        dto.setCompleted(source.isCompleted());
        dto.setScore(source.getScore());

        if (source.getFinishedAt() != null) {
            dto.setFinishedAtFormatted(source.getFinishedAt().format(FORMATTER));
        } else {
            dto.setFinishedAtFormatted("—");
        }

        return dto;
    }
}