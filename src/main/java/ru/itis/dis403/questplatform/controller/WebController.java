package ru.itis.dis403.questplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import ru.itis.dis403.questplatform.form.ReviewForm;
import ru.itis.dis403.questplatform.model.Difficulty;
import ru.itis.dis403.questplatform.model.Quest;
import ru.itis.dis403.questplatform.model.User;
import ru.itis.dis403.questplatform.service.QuestService;
import ru.itis.dis403.questplatform.service.ReviewService;
import ru.itis.dis403.questplatform.service.UserService;
import ru.itis.dis403.questplatform.service.ProgressService;
import ru.itis.dis403.questplatform.util.EmailService;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final QuestService questService;
    private final UserService userService;
    private final ProgressService progressService;
    private final ReviewService reviewService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("quests", questService.getAllQuestsAsDTO());
        return "index";
    }

    @GetMapping("/quests/{id}")
    public String questDetails(@PathVariable Long id, Model model) {
        model.addAttribute("quest", questService.getQuestById(id));
        return "quest/view";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Authentication authentication) {
        String email = authentication.getName();

        var user = userService.findByEmail(email);

        model.addAttribute("currentUser", user);


        return "profile/index";
    }
    @GetMapping("/quests/{id}/play")
    public String playPage(@PathVariable Long id, Model model) {
        model.addAttribute("quest", questService.getQuestWithTasks(id));
        return "quest/play";
    }

    @GetMapping("/profile/history")
    public String historyPage(Model model, Authentication authentication) {
        String email = authentication.getName();
        var user = userService.findByEmail(email);
        model.addAttribute("history", progressService.getUserHistory(user.getId()));
        return "profile/history";
    }

    @GetMapping("/quests/search")
    public String searchQuests(
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String category,
            Model model) {

        if (difficulty != null || (category != null && !category.isBlank())) {
            model.addAttribute("quests", questService.filterByCriteria(difficulty, category));
            model.addAttribute("activeFilters", true);
        } else {
            model.addAttribute("quests", questService.getAllQuestsAsDTO());
            model.addAttribute("activeFilters", false);
        }

        model.addAttribute("selectedDifficulty", difficulty != null ? difficulty.name() : "");
        model.addAttribute("selectedCategory", category != null ? category : "");
        return "quest/search";
    }

    @GetMapping("/quests/{id}/reviews")
    public String reviewsPage(@PathVariable Long id, Model model, Authentication authentication) {
        Quest quest = questService.getQuestById(id);
        model.addAttribute("quest", quest);
        model.addAttribute("reviews", reviewService.getReviewsByQuestId(id));

        model.addAttribute("reviewForm", new ReviewForm());

        model.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());

        return "quest/reviews";
    }

    @PostMapping("/quests/{id}/reviews")
    public String submitReview(@PathVariable Long id,
                               @Valid @ModelAttribute("reviewForm") ReviewForm form,
                               BindingResult bindingResult,
                               Authentication authentication,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("quest", questService.getQuestById(id));
            model.addAttribute("reviews", reviewService.getReviewsByQuestId(id));
            model.addAttribute("isAuthenticated", true);
            return "quest/reviews";
        }

        String email = authentication.getName();
        User user = userService.findByEmail(email);

        reviewService.saveReview(id, user.getId(), form);
        redirectAttributes.addFlashAttribute("successMessage", "Спасибо! Ваш отзыв успешно добавлен.");

        return "redirect:/quests/" + id + "/reviews";
    }

    @GetMapping("/quests/popular")
    public String popularQuests(Model model) {
        model.addAttribute("quests", questService.getPopularQuests());
        model.addAttribute("pageTitle", "Популярные квесты");
        return "quest/popular";
    }
}