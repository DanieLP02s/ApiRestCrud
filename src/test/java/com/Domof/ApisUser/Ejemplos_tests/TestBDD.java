package com.Domof.ApisUser.Ejemplos_tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBDD {

    // Paso 1: Escribir la especificación (Given-When-Then)

    @Test
    @DisplayName("Debe autenticar correctamente a un usuario válido")
    public void debeAutenticarUsuarioValido() {
        // Given
        AutenticacionService autenticacionService = new AutenticacionService();
        String username = "usuario";
        String password = "password";

        // When
        boolean autenticado = autenticacionService.autenticar(username, password);

        // Then
        assertTrue(autenticado);
    }


    // Aquí, AutenticacionService es la clase que queremos probar y autenticar()
    // es el método que implementaremos.

    // Paso 2: Implementar el código para satisfacer la especificación (Refactor/Implementación)

    public class AutenticacionService {
        public boolean autenticar(String username, String password) {
            return username.equals("usuario") && password.equals("password");
        }
    }

    // Implementamos el método autenticar() para que pase el test especificado.
    //
    // Paso 3: Validar el comportamiento con el código implementado (Verify)
    //
    // Ejecutamos el test y verificamos que el comportamiento esperado se cumpla correctamente.

}
