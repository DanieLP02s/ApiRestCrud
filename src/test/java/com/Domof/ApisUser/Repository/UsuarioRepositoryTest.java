package com.Domof.ApisUser.Repository;

import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import com.Domof.ApisUser.ApisRest.Repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest // optimiza pruebas de operaciones CRUD en repositorios JPA de Spring Boot, enfocándose en repositorios y entidades.
@DisplayName("Pruebas para UsuarioRepository")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio real a ser probado

    @MockBean
    private UsuarioRepository mockUsuarioRepository; // Repositorio mock para simular el comportamiento del repositorio

    @Test
    @DisplayName("Test para guardar un usuario")
    public void testGuardarUsuario() {
        // Given (Dado): Creación de un usuario simulado
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        Usuario savedUsuario = usuarioRepository.save(usuario);

        assertEquals("Juan", savedUsuario.getNombre()); // Then: Verifica que el nombre del usuario guardado sea "Juan"
    }

    @Test
    @DisplayName("Test para encontrar un usuario por ID")
    public void testEncontrarUsuarioPorId() {
        // Given (Dado): Creación de un usuario simulado
        Usuario usuario = new Usuario("Dann", "Dann@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        Optional<Usuario> foundUsuario = usuarioRepository.findById(usuario.getId());

        assertTrue(foundUsuario.isPresent()); // Then: Verifica que se encontró el usuario
        assertEquals(usuario.getNombre(), foundUsuario.get().getNombre()); // Then: Verifica que el nombre del usuario encontrado sea "Dann"
    }

    @Test
    @DisplayName("Test para actualizar un usuario por ID")
    public void testActualizarUsuarioPorId() {
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");
        usuario.setId(1L);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = usuarioRepository.save(usuario);

        assertEquals("Juan", updatedUsuario.getNombre());
        verify(usuarioRepository, times(1)).save(usuario); // Verifica que el método save del repositorio se haya llamado exactamente una vez con el usuario actualizado
    }



    @Test
    @DisplayName("Test para encontrar todos los usuarios")
    public void testEncontrarTodosLosUsuarios() {
        // Given (Dado): Creación de dos usuarios simulados
        Usuario usuario1 = new Usuario("Juan", "juan@gmail.com", "1234");
        Usuario usuario2 = new Usuario("Dann", "Dann@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        List<Usuario> usuarios = usuarioRepository.findAll();

        assertEquals(2, usuarios.size()); // Then: Verifica que se devuelvan 2 usuarios
    }

    @Test
    @DisplayName("Test para eliminar un usuario")
    public void testEliminarUsuario() {
        // Given (Dado): Creación de un usuario simulado
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");

        // When (Cuando): Ejecución de la acción de guardado y eliminación
        usuarioRepository.save(usuario);
        usuarioRepository.deleteById(usuario.getId());

        // Then (Entonces): Verificación de que se llamó al método correspondiente en el repositorio
        verify(usuarioRepository, times(1)).deleteById(usuario.getId()); // Then: Verifica que se eliminó el usuario una vez
    }
}
