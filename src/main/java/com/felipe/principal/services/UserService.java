package com.felipe.principal.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.felipe.principal.models.User;
import com.felipe.principal.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends BaseService{
    
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Object findById(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Object create(Object object) {
        // TODO Auto-generated method stub
        return userRepository.save((User) object);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }

    public BindingResult validateRegister(User user, BindingResult validations) {
        if(!user.getPasswordForm().equals(user.getPasswordConfirm())) {
            validations.rejectValue("passwordConfirm", "errorPasswordConfirm", "Las contraseñas deben ser iguales");
        }
        User emailExist = userRepository.findByEmail(user.getEmail());
        if (emailExist !=null) {
            validations.rejectValue("email", "emailAlreadyExist", "EL correo ya está registrado");
        }
        if (!validations.hasErrors()) {
            String encryptedPassword = BCrypt.hashpw(user.getPasswordForm(), BCrypt.gensalt());
            user.setPassword(encryptedPassword);
        }
        return validations;
    }
}
