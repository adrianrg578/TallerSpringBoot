package com.example.api.service;

import com.example.api.model.Usuario;
import com.example.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario){
        if (usuarioRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty() && 
            usuarioRepository.existsByTelefono(usuario.getTelefono())) {
            throw new RuntimeException("El teléfono ya está en uso");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        // Verificar si el correo electrónico ya está en uso por otro usuario
        if (!usuarioExistente.getCorreoElectronico().equals(usuario.getCorreoElectronico())) {
            if (usuarioRepository.existsByCorreoElectronico(usuario.getCorreoElectronico())) {
                throw new RuntimeException("El correo electrónico ya está en uso");
            }
        }

        // Verificar si el teléfono ya está en uso por otro usuario
        if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty() && 
            !usuarioExistente.getTelefono().equals(usuario.getTelefono())) {
            if (usuarioRepository.existsByTelefono(usuario.getTelefono())) {
                throw new RuntimeException("El teléfono ya está en uso");
            }
        }

        usuarioExistente.setNombres(usuario.getNombres());
        usuarioExistente.setApellidos(usuario.getApellidos());
        usuarioExistente.setEdad(usuario.getEdad());
        usuarioExistente.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioExistente.setTelefono(usuario.getTelefono());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }
}
