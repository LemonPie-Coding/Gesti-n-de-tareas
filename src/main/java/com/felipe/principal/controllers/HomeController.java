package com.felipe.principal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.felipe.principal.models.User;
import com.felipe.principal.models.forms.Session;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
public class HomeController {

    // @GetMapping("/home")
    // public String home(HttpSession session, Model model) {
    // User currentUser = (User) session.getAttribute("currentUser");

    //     if (currentUser == null) {
    //         return "redirect:/auth"; // Redirige al login si no hay usuario en sesión
    //     }

    //     model.addAttribute("currentUser", currentUser);
    //     return "home.jsp"; // Retorna la vista home.jsp
    // }
    
    @GetMapping("/")
    public String auth(Model model, HttpSession session) {
        session.invalidate(); 
        //Para que tu jsp funcione, en el form tanto para modelAttribute="login" y modelAttribute="user" debes agregarlos a tu modelo.
        model.addAttribute("user", new User()); // Agrega el atributo 'user' al modelo
        model.addAttribute("session", new Session()); // Agrega el atributo 'login' al modelo
        return "auth.jsp";
    }
}
