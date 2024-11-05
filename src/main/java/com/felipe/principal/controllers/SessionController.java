package com.felipe.principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.felipe.principal.models.User;
import com.felipe.principal.models.forms.Session;
import com.felipe.principal.services.SessionService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private final SessionService sessionService;

    @PostMapping("process/login")
    public String login(
        @Valid @ModelAttribute Session session, 
        BindingResult validations, 
        HttpSession currentSession,
        Model model) {
            validations = sessionService.validateLogin(session.getEmail(), session.getPassword(), currentSession, validations);
            if (validations.hasErrors()) {
                model.addAttribute("user", new User());
                return "auth.jsp";
            }
    
            return "redirect:/jobs";
        }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        //TODO: process POST request
        sessionService.logout(session);
        return "redirect:/auth";
    }
    
}
