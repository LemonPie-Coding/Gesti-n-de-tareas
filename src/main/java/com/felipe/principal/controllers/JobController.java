package com.felipe.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.felipe.principal.models.Job;
import com.felipe.principal.models.Priority;
import com.felipe.principal.models.User;
import com.felipe.principal.services.JobService;
import com.felipe.principal.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private final JobService jobService;
    private final UserService userService;

    @GetMapping("")
    public String addJobForm(
        HttpSession session,
        @ModelAttribute("job") Job job,
        @RequestParam(value = "sort", required = false) String sort,
        Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/auth";
          }  //Si no hay ninguna sesion acitva redirigir al inicio para evitar registros al entrar manualmente a la ruta desde la url
        //Metodo para ordenar de manera ASC y DESC
        List<Job> jobsOrder;
        if ("asc".equals(sort)) {
            jobsOrder = jobService.findAllByOrderByPriorityAsc();
        } else if ("desc".equals(sort)) {
            jobsOrder = jobService.findAllByOrderByPriorityDesc();
        } else {
            jobsOrder = (List<Job>) jobService.findAll();  // Sin orden específico
        }
        model.addAttribute("jobs", jobsOrder);  // Mantener la lista ordenada
        model.addAttribute("currentUser", currentUser);
        return "jobs.jsp";
    }
    //mostrar jobs
    @GetMapping("/add")
    public String create(
        @ModelAttribute("job") Job job, 
        Model model,
        HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
                return "redirect:/auth"; // Redirige al login si no hay usuario en sesión
            }
        List<User> userList = userService.findAll();
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("userList", userList);
        return "createJob.jsp";
    }
    //detalles de jobs
    @GetMapping("/details/{id}")
    public String deployJobDetails(@PathVariable("id")Long id, Model model) {
        Job jobDetail = (Job) jobService.findById(id);
        model.addAttribute("jobDetail", jobDetail);
        return "jobDetails.jsp";
    }
    //editar jobs
    @GetMapping("/edit/{id}")
    public String formEditJob(
        @PathVariable("id")Long id,
        Model model) {
            Job actualJob = (Job) jobService.findById(id);
            List<User> userList = userService.findAll();
            model.addAttribute("job", actualJob);
            model.addAttribute("userList", userList);
        return "editJob.jsp";
    }
    //actualizar jobs
@PutMapping("/process/edit/{id}")
public String processEditJob(
    @Valid @ModelAttribute("newJob") Job newJob,
    BindingResult validation,  // Este debe estar inmediatamente después de @ModelAttribute
    @PathVariable("id") Long id,
    HttpSession session,
    Model model) {

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        return "redirect:/auth";
    }
    newJob.setCreator(currentUser.getName()); // Si `creator` es un campo de tipo String en Job
    // Validación: No permitir que un usuario tenga más de 3 tareas
        //PROFE NO SE POR QUE NO ME DEJA USAR LA MISMA VALIDACION DE LAS 3 TAREAS POR USUARIO COMO LO HICE CON EL POST MAPPING, ASI QUE LAMENTABLEMENTE SI SE PUEDEN ASIGNAR MAS DE 3 TAREAS A UN USUARIO MEDIANTE LA EDICION DE UNA TAREA.

        // for (User user : newJob.getUsers()) {
        // User fullUser = (User) userService.findById(user.getId());
        // int taskCount = jobService.countJobsByUserId(user.getId());
        // if (taskCount >= 3) {
        //     validation.rejectValue("priority", "error.user", "El usuario " + fullUser.getName() + " ya tiene 3 tareas asignadas.");
        //     }
        // }

        
    // Verifica si hay errores en las validaciones
    if (validation.hasErrors()) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("priorities", Priority.values());
        return "editJob.jsp";  // Vuelve al formulario de edición si hay errores
    }

    newJob.setId(id);
    jobService.update(newJob);
    return "redirect:/jobs/details/" + id;
}

    //create job
    @PostMapping("/add")
    public String saveJob(
        @ModelAttribute("job")Job job,
        HttpSession session,
        BindingResult validation,
        Model model) {
        //Tomar el usuario actual
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/auth";
        }
        // Establecer el creador de la tarea como el usuario que está en sesión
        job.setCreator(currentUser.getName());  // Si `creator` es un campo de tipo String en Job
         // Validación: No permitir que un usuario tenga más de 3 tareas
        for (User user : job.getUsers()) {
        //Tuve que buscar al usuario por id, ya que no me estaba dando el nombre, me aparecia como null.
            User fullUser = (User) userService.findById(user.getId());
            int taskCount = jobService.countJobsByUserId(user.getId());
            if (taskCount >= 3) {
                validation.rejectValue("priority", "error.user", "El usuario " + fullUser.getName() + " ya tiene 3 tareas asignadas.");
            }
        }
        if (validation.hasErrors()) {
            List<User> userList = userService.findAll();
            model.addAttribute("userList", userList);
            model.addAttribute("priorities", Priority.values());
            return "createJob.jsp";
        }
        jobService.create(job);
        return "redirect:/jobs";
    }

    //delete job
    @DeleteMapping("/delete/{id}")
    public String processDeleteJob(@PathVariable("id")Long id) {
        jobService.deleteById(id);
        return "redirect:/jobs";
    }
}   
