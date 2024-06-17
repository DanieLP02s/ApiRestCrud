package com.Domof.ApisUser.ApisRest.Controller;

import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import com.Domof.ApisUser.ApisRest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

      //Listar Todos los Usuarios

    @GetMapping ("/usuarios")
    public List<Usuario>obtenerusuarios (){
        return usuarioService.obtenerUsuarios();
    }

      // Buscar Usuario por ID

    @GetMapping ("/usuarios/{id}")
    ResponseEntity<Usuario> obtenerUsuarioId(@PathVariable Long id){
        try {
            Usuario usuario = usuarioService.obtenerUsuariosPorId(id);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

        }catch (Exception exception){
        return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
      }

      //Guardar Usuario

      @PostMapping ("/usuarios")
        public void registroUsuarios(@RequestBody Usuario usuario){
        usuarioService.crearUsuario(usuario);
      }

      // Atualizar Usuario por ID

      @PutMapping ("/usuarios/{id}")
      public ResponseEntity<?> ActualizarPorId (@RequestBody Usuario usuario, @PathVariable Long id){
        try {
            Usuario usuarioPorId = usuarioService.obtenerUsuariosPorId(id);
            usuarioPorId.setEmail(usuario.getEmail());
            usuarioPorId.setNombre(usuario.getNombre());
            usuarioPorId.setPassword(usuario.getPassword());

            usuarioService.crearUsuario(usuarioPorId);
            return new ResponseEntity<Usuario>(HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
      }

      //Eliminar Usuario Por ID

      @DeleteMapping ("/usuario/{id}")
      public void eliminaUsuarios (@PathVariable Long id){
        usuarioService.eliminarUsuarios(id);
      }







}
