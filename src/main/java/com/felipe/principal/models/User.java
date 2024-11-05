package com.felipe.principal.models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")

public class User extends Base {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 16, message = "El campo debe contener entre 3 y 16 caracteres.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "El campo solo puede contener letras sin espacios.")
    private String name;

    @NotNull(message = "El email es requerido")
    @Email(message = "El email no es valido")
    private String email;

    private String password;

    @NotBlank(message="Las contraseñas no pueden quedar vacías")
    @Transient
    @Size(min = 5, max = 16, message = "Debe contener entre 7 y 16 caracteres.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{5,16}$", 
    message = "Debe contener al menos una mayúscula, una minúscula y un número.")
    private String passwordForm;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(
        name = "asignations",
        joinColumns= @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "job_id")
        )
    private List<Job> jobs;
}

