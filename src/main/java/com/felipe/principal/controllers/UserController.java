package com.felipe.principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipe.principal.models.User;
import com.felipe.principal.models.forms.Session;
import com.felipe.principal.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    private final UserService userService;
    
    @GetMapping("")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("session", new Session());
        return "auth.jsp";
    }

    @PostMapping("/process/register")
    public String saveUser(
        @Valid
        @ModelAttribute("user")User user,
        BindingResult validations,
        @ModelAttribute("session")Session session,
        RedirectAttributes redirectAttributes
        ) {
            validations = userService.validateRegister(user, validations);
            if(validations.hasErrors()) {
                return "auth.jsp";
            }
        userService.create(user);
         // Agregar mensaje de éxito usando RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Usuario registrado con éxito. Por favor, inicia sesión.");
        return "redirect:/";
    }
}
