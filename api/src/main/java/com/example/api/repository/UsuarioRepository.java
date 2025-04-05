package com.example.api.repository;

import com.example.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
    boolean existsByTelefono(String telefono);
}
