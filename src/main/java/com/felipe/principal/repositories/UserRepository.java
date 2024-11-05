package com.felipe.principal.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.felipe.principal.models.User;


public interface UserRepository extends CrudRepository<User, Long> {

    
    //buscar todos los usuarios
    List<User> findAll();
    //buscar un usuario por email
    <Optional>User findByEmail(String email);
    //buscar un usuario por id
    <Optinal>User findById(long id);
    //crear un usuario y actualizar un usuario
    User save(User user);
    //eliminar un usuario
    void deleteById(Long id);
}
