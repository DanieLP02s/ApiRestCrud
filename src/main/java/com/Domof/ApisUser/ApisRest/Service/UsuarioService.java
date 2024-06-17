package com.Domof.ApisUser.ApisRest.Service;

import com.Domof.ApisUser.ApisRest.Entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obtenerUsuarios();

    Usuario obtenerUsuariosPorId(Long id);

    Usuario crearUsuario(Usuario usuario);

    Usuario actualizarUsuarioPorId(Long id);

    void eliminarUsuarios(Long id);

}
