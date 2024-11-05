package com.felipe.principal.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.felipe.principal.models.User;
import com.felipe.principal.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    
    @Autowired
    private UserRepository userRepository;

    public BindingResult validateLogin(String email, String password, HttpSession session, BindingResult validations) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            validations.rejectValue("email", "emailNotRegistred", "Este email no está registrado");
        } else if (!BCrypt.checkpw(password, user.getPassword())) {
            validations.rejectValue("password", "incorrectPassword", "Ingrese una contraseña válida");
        }else{
            session.setAttribute("currentUser", user);
        }
        return validations;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
