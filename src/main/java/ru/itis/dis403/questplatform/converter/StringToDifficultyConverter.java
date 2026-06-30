package ru.itis.dis403.questplatform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.dis403.questplatform.model.Difficulty;

@Component
public class StringToDifficultyConverter implements Converter<String, Difficulty> {
    @Override
    public Difficulty convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }
        return Difficulty.valueOf(source.toUpperCase());
    }
}