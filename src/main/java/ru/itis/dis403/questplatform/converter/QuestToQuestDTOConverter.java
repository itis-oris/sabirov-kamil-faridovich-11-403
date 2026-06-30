package ru.itis.dis403.questplatform.converter;

import org.hibernate.Hibernate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.dis403.questplatform.dto.AnswerDTO;
import ru.itis.dis403.questplatform.dto.QuestDTO;
import ru.itis.dis403.questplatform.dto.TaskDTO;
import ru.itis.dis403.questplatform.model.Quest;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestToQuestDTOConverter implements Converter<Quest, QuestDTO> {

    @Override
    public QuestDTO convert(Quest source) {
        QuestDTO dto = new QuestDTO();
        dto.setId(source.getId());
        dto.setTitle(source.getTitle());
        dto.setDescription(source.getDescription());
        dto.setDifficulty(source.getDifficulty().name());
        dto.setCategory(source.getCategory());
        return dto;
    }
}