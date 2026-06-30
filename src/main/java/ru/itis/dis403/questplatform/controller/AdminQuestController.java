package ru.itis.dis403.questplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.dis403.questplatform.form.AnswerForm;
import ru.itis.dis403.questplatform.form.QuestForm;
import ru.itis.dis403.questplatform.form.TaskForm;
import ru.itis.dis403.questplatform.model.Answer;
import ru.itis.dis403.questplatform.model.Task;
import ru.itis.dis403.questplatform.service.QuestService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/quests")
@RequiredArgsConstructor
public class AdminQuestController {
    private final QuestService questService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(100);
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("quests", questService.getAllQuestsAsDTO());
        return "admin/quest_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("form", new QuestForm());
        return "admin/quest_form";
    }

    @PostMapping("/create")
    public String createProcess(@Valid @ModelAttribute("form") QuestForm form,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors on quest creation: {}", bindingResult.getAllErrors());
            return "admin/quest_form";
        }

        try {
            questService.create(form);
            log.info("Quest created: {}", form.getTitle());

            redirectAttributes.addFlashAttribute("successMessage", "Квест успешно создан!");

            return "redirect:/admin/quests";

        } catch (Exception e) {
            log.error("Failed to create quest", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка: " + e.getMessage());
            return "redirect:/admin/quests/create";
        }
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var quest = questService.getQuestById(id);
        QuestForm form = new QuestForm();
        form.setTitle(quest.getTitle());
        form.setDescription(quest.getDescription());
        form.setDifficulty(quest.getDifficulty().name());
        form.setCategory(quest.getCategory());

        List<TaskForm> taskForms = new ArrayList<>();

        for (Task task : quest.getTasks()) {
            TaskForm taskForm = new TaskForm();
            taskForm.setQuestion(task.getQuestion());

            List<AnswerForm> answerForms = new ArrayList<>();

            for (Answer answer : task.getAnswers()) {
                AnswerForm answerForm = new AnswerForm();
                answerForm.setText(answer.getText());

                answerForm.setCorrect(answer.isCorrect());
                answerForms.add(answerForm);
            }

            taskForm.setAnswers(answerForms);
            taskForms.add(taskForm);
        }

        form.setTasks(taskForms);

        model.addAttribute("form", form);
        model.addAttribute("id", id);
        return "admin/quest_form";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable Long id,
                              @Valid @ModelAttribute("form") QuestForm form,
                              BindingResult result) {
        if (result.hasErrors()) return "admin/quest_form";
        questService.update(id, form);
        return "redirect:/admin/quests";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        questService.delete(id);
        return "redirect:/admin/quests";
    }
}