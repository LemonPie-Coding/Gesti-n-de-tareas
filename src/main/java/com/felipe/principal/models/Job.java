package com.felipe.principal.models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job extends Base {
    
    private String description;

    private String creator;

    @Enumerated(EnumType.STRING) // Almacena el nombre del enum como un String en la base de datos
    private Priority priority;

    @ManyToMany
    @JoinTable (
        name = "asignations",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn (name = "user_id")
    )
    private List<User> users;

}
