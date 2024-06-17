package com.Domof.ApisUser.Service;

import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import com.Domof.ApisUser.ApisRest.Repository.UsuarioRepository;
import com.Domof.ApisUser.ApisRest.Service.Impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas para UsuarioServiceImpl")
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository; // Mock del repositorio UsuarioRepository

    @InjectMocks
    private UsuarioServiceImpl usuarioService; // Instancia del servicio UsuarioServiceImpl a testear

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicialización de los mocks antes de cada prueba
    }

    @Test
    @DisplayName("Test para obtener todos los usuarios")
    public void testObtenerUsuarios() {
        // Given (Dado): Creación de dos usuarios simulados
        Usuario usuario1 = new Usuario("Juan", "juan@gmail.com", "1234");
        Usuario usuario2 = new Usuario("Ana", "ana@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();

        assertEquals(2, usuarios.size()); // Then: Verifica que se devuelvan 2 usuarios
        verify(usuarioRepository, times(1)).findAll(); // Then: Verifica que el método findAll del repositorio se haya llamado exactamente una vez
    }

    @Test
    @DisplayName("Test para obtener un usuario por ID")
    public void testObtenerUsuarioPorId() {
        // Given (Dado): Definición de un ID de usuario y un usuario simulado
        Long userId = 1L;
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        Usuario result = usuarioService.obtenerUsuariosPorId(userId);

        assertEquals(usuario, result); // Then: Verifica que el usuario devuelto sea el esperado
        verify(usuarioRepository, times(1)).findById(userId); // Then: Verifica que el método findById del repositorio se haya llamado exactamente una vez
    }

    @Test
    @DisplayName("Test para crear un usuario")
    public void testCrearUsuario() {
        // Given (Dado): Creación de un usuario simulado
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del repositorio mock
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        Usuario result = usuarioService.crearUsuario(usuario);

        assertEquals(usuario, result); // Then: Verifica que el usuario creado sea el esperado
        verify(usuarioRepository, times(1)).save(usuario); // Then: Verifica que el método save del repositorio se haya llamado exactamente una vez
    }



    @Test
    @DisplayName("Test para eliminar un usuario")
    public void testEliminarUsuario() {
        // Given (Dado): Definición de un ID de usuario a eliminar
        Long userId = 1L;

        // When (Cuando): Ejecución de la acción de eliminación
        usuarioService.eliminarUsuarios(userId);

        // Then (Entonces): Verificación de que se llamó al método correspondiente en el repositorio
        verify(usuarioRepository, times(1)).deleteById(userId); // Then: Verifica que el método deleteById del repositorio se haya llamado exactamente una vez
    }
}
