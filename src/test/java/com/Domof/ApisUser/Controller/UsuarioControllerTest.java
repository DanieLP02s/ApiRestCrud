package com.Domof.ApisUser.Controller;

import com.Domof.ApisUser.ApisRest.Controller.UsuarioController;
import com.Domof.ApisUser.ApisRest.Entity.Usuario;
import com.Domof.ApisUser.ApisRest.Service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas para UsuarioController")
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService; // Mock del servicio UsuarioService

    @InjectMocks
    private UsuarioController usuarioController; // Instancia del controlador UsuarioController a testear

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicialización de los mocks antes de cada prueba
    }

    @Test
    @DisplayName("Test para obtener todos los usuarios")
    public void testObtenerUsuarios() {
        // Given (Dado): Creación de datos de usuario simulados
        Usuario usuario1 = new Usuario("Dann", "Dann@gmail.com", "1234");
        Usuario usuario2 = new Usuario("Ana", "Ana@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del servicio mock
        when(usuarioService.obtenerUsuarios()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        List<Usuario> usuarios = usuarioController.obtenerusuarios();

        assertEquals(2, usuarios.size()); // Verifica que se devuelvan 2 usuarios
        verify(usuarioService, times(1)).obtenerUsuarios(); // Verifica que el método se haya llamado exactamente una vez
    }


    @Test
    @DisplayName("Test para obtener un usuario por ID")
    public void testObtenerUsuarioPorId() {
        // Given (Dado): Definición de un ID de usuario y un usuario simulado
        Long userId = 1L;
        Usuario usuario = new Usuario("Dann", "Dann@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del servicio mock
        when(usuarioService.obtenerUsuariosPorId(userId)).thenReturn(usuario);

        // Then (Entonces): Ejecución de la acción y verificación del resultado
        ResponseEntity<Usuario> response = usuarioController.obtenerUsuarioId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica que se devuelva HttpStatus.OK
        assertEquals(usuario, response.getBody()); // Verifica que el usuario devuelto sea el esperado
        verify(usuarioService, times(1)).obtenerUsuariosPorId(userId); // Verifica que el método se haya llamado exactamente una vez
    }


    @Test
    @DisplayName("Test para registrar un usuario")
    public void testRegistrarUsuario() {
        // Given (Dado): Creación de un usuario simulado
        Usuario usuario = new Usuario("Juan", "juan@gmail.com", "1234");

        // When (Cuando): Ejecución de la acción de registro
        usuarioController.registroUsuarios(usuario);

        // Then (Entonces): Verificación de que se llamó al método correspondiente en el servicio
        verify(usuarioService, times(1)).crearUsuario(usuario); // Verifica que el método se haya llamado exactamente una vez
    }


    @Test
    @DisplayName("Test para actualizar un usuario por ID")
    public void testActualizarUsuarioPorId() {
        // Given (Dado): Definición de un ID de usuario y un usuario simulado
        Long userId = 1L;
        Usuario usuario = new Usuario(userId, "Juan", "juan@gmail.com", "1234");

        // When (Cuando): Configuración del comportamiento del servicio mock
        when(usuarioService.obtenerUsuariosPorId(userId)).thenReturn(usuario);
        when(usuarioService.crearUsuario(usuario)).thenReturn(usuario);

        // Ejecución del método a probar
        ResponseEntity<?> responseEntity = usuarioController.ActualizarPorId(usuario, userId);

        // Then (Entonces): Verificación del resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Verifica que se haya devuelto un HttpStatus.OK
        verify(usuarioService, times(1)).obtenerUsuariosPorId(userId); // Verifica que se llamó al método obtenerUsuariosPorId del servicio exactamente una vez con el ID
        verify(usuarioService, times(1)).crearUsuario(usuario); // Verifica que se llamó al método crearUsuario del servicio exactamente una vez con el usuario actualizado
    }


    @Test
    @DisplayName("Test para eliminar un usuario")
    public void testEliminarUsuario() {
        // Given (Dado): Definición de un ID de usuario a eliminar
        Long userId = 1L;

        // When (Cuando): Ejecución de la acción de eliminación
        usuarioController.eliminaUsuarios(userId);

        // Then (Entonces): Verificación de que se llamó al método correspondiente en el servicio
        verify(usuarioService, times(1)).eliminarUsuarios(userId); // Verifica que el método se haya llamado exactamente una vez
    }

}

