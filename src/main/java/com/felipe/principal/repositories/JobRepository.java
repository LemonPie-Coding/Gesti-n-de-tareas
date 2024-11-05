package com.felipe.principal.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.felipe.principal.models.Job;

public interface JobRepository extends CrudRepository<Job, Long>{

    //buscar todos los jobs
    List<Job> findAll();

    //buscar un job por id
    Optional<Job> findById(long id);
    
    //eliminar un curso
    void deleteById(Long id);

    //crear y actualizar un job
    Job save(Job course);

    int countByUsers_Id(Long userId);

    //para ordenar ascendente
    List<Job> findAllByOrderByPriorityAsc();
    //para ordenar descendente
    List<Job> findAllByOrderByPriorityDesc();
}
