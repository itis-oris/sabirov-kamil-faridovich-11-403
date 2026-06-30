package ru.itis.dis403.questplatform.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, Model model, HttpServletRequest request) {
        log.warn("Page not found: {}", ex.getRequestURL());
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("isAuthenticated", isAuthenticated());
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("status", 404);
        model.addAttribute("message", "Страница не найдена: " + ex.getRequestURL());
        model.addAttribute("path", ex.getRequestURL());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model, HttpServletRequest request) {
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("isAuthenticated", isAuthenticated());
        model.addAttribute("isAdmin", isAdmin());
        log.error("Server error", ex);
        model.addAttribute("status", 500);
        model.addAttribute("message", ex.getMessage());
        return "500";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model, HttpServletRequest request) {
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("isAuthenticated", isAuthenticated());
        model.addAttribute("isAdmin", isAdmin());
        log.warn("Business logic error: {}", ex.getMessage());
        model.addAttribute("status", 400);
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException ex, Model model, HttpServletRequest request) {
        log.warn("Static resource not found: {}", ex.getResourcePath());
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("isAuthenticated", isAuthenticated());
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("status", 404);
        model.addAttribute("message", "Ресурс не найден: " + ex.getResourcePath());
        model.addAttribute("path", request.getRequestURI());
        return "error/404";
    }
    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() &&
                auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}