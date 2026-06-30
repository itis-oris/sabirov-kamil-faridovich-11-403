package ru.itis.dis403.questplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.questplatform.converter.QuestToQuestDTOConverter;
import ru.itis.dis403.questplatform.converter.StringToDifficultyConverter;
import ru.itis.dis403.questplatform.dto.QuestDTO;
import ru.itis.dis403.questplatform.form.AnswerForm;
import ru.itis.dis403.questplatform.form.QuestForm;
import ru.itis.dis403.questplatform.form.TaskForm;
import ru.itis.dis403.questplatform.model.Answer;
import ru.itis.dis403.questplatform.model.Difficulty;
import ru.itis.dis403.questplatform.model.Quest;
import ru.itis.dis403.questplatform.model.Task;
import ru.itis.dis403.questplatform.repository.QuestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestService {
    private final QuestRepository questRepository;
    private final QuestToQuestDTOConverter questToQuestDTOConverter;
    private final StringToDifficultyConverter stringToDifficultyConverter;



    @Cacheable(value = "quests_main")
    public List<QuestDTO> getAllQuestsAsDTO() {
        return questRepository.findAll().stream()
                .map(questToQuestDTOConverter::convert)
                .collect(Collectors.toList());
    }


    public Quest getQuestById(Long id) {
        return questRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Квест не найден: " + id));
    }

    @Transactional
    @CacheEvict(value = "quests_main", allEntries = true)
    public Quest create(QuestForm form) {
        log.info("Creating quest: {}", form.getTitle());
        Quest quest = new Quest();
        quest.setTitle(form.getTitle());
        quest.setDescription(form.getDescription());
        quest.setCategory(form.getCategory());
        quest.setDifficulty(stringToDifficultyConverter.convert(form.getDifficulty()));


        Quest saved = questRepository.save(quest);
        if (form.getTasks() != null) {
            for (TaskForm taskForm : form.getTasks()) {
                if (taskForm.getQuestion() == null || taskForm.getQuestion().isBlank()) {
                    continue;
                }

                Task task = new Task();
                task.setQuestion(taskForm.getQuestion());
                task.setQuest(saved);


                if (taskForm.getAnswers() != null) {
                    Integer correctIdx = taskForm.getCorrectAnswerIndex();

                    for (int i = 0; i < taskForm.getAnswers().size(); i++) {
                        AnswerForm answerForm = taskForm.getAnswers().get(i);
                        if (answerForm.getText() == null || answerForm.getText().isBlank()) {
                            continue;
                        }

                        Answer answer = new Answer();
                        answer.setText(answerForm.getText());


                        answer.setCorrect(correctIdx != null && correctIdx == i);

                        answer.setTask(task);
                        task.getAnswers().add(answer);
                    }
                }

                saved.getTasks().add(task);
            }
        }


        Quest result = questRepository.save(saved);
        log.info("Quest created with id: {}", result.getId());
        return result;
    }

    @Transactional
    @CacheEvict(value = "quests_main", allEntries = true)
    public Quest update(Long id, QuestForm form) {
        log.info("Updating quest: {}", id);
        Quest quest = getQuestById(id);
        quest.setTitle(form.getTitle());
        quest.setDescription(form.getDescription());
        quest.setCategory(form.getCategory());
        quest.setDifficulty(Difficulty.valueOf(form.getDifficulty().toUpperCase()));

        Quest updated = questRepository.save(quest);
        log.info("Quest updated: {}", id);
        return updated;
    }

    @Transactional
    @CacheEvict(value = "quests_main", allEntries = true)
    public void delete(Long id) {
        log.info("Deleting quest: {}", id);
        questRepository.deleteById(id);
        log.info("Quest deleted: {}", id);
    }

    public List<Quest> filterByCriteria(Difficulty difficulty, String category) {
        return questRepository.findByDifficultyAndCategory(difficulty, category);
    }


    public Quest getQuestWithTasks(Long id) {
        return questRepository.findById(id).orElseThrow(() -> new RuntimeException("Квест не найден"));
    }

    public List<QuestDTO> getPopularQuests() {
        return questRepository.findPopularQuests(4.0).stream()
                .map(questToQuestDTOConverter::convert)
                .collect(Collectors.toList());
    }
}