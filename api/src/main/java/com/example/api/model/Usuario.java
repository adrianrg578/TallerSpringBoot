package com.example.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "Usuarios")
@Data

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombres", nullable = false, length = 50)
    @NotBlank(message = "Los nombres deben ser obligatorios")
    @Size(max = 50, message = "Los nombres no pueden llevar mas de 50 caracteres")
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 50, message = "Los apellidos no pueden exceder los 50 caracteres")
    private String apellidos;

    @Column(name = "edad")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
    private String correoElectronico;

    @Column(name = "telefono", unique = true, length = 20)
    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String telefono;
}
