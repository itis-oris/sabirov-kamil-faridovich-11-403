package ru.itis.dis403.questplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.dis403.questplatform.form.RegisterForm;
import ru.itis.dis403.questplatform.service.UserService;
import ru.itis.dis403.questplatform.util.EmailService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/auth/login")
    public String loginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String loginMessage = (String) session.getAttribute("loginMessage");
            if (loginMessage != null) {
                model.addAttribute("loginMessage", loginMessage);
                session.removeAttribute("loginMessage");
            }
        }
        return "auth/login";
    }

    @GetMapping("/auth/register")
    public String registerPage(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String registerProcess(@ModelAttribute("form") RegisterForm form,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            log.warn("Registration validation error");
            return "auth/register";
        }
        try {
            userService.register(form);
            return "redirect:/auth/login";
        } catch (RuntimeException e) {
            log.error("Registration failed: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }
}