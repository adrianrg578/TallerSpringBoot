package com.example.api.service;

import com.example.api.model.Usuario;

import java.util.List;

public interface UsuarioService{
    List<Usuario> obtenerTodosUsuarios();
    Usuario obtenerUsuarioPorId(Long Id);
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(Long Id, Usuario usuario);
    void eliminarUsuario(Long Id);
}
