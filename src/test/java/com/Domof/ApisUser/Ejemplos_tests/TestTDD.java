package com.Domof.ApisUser.Ejemplos_tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTDD {


    // Tarea: Crear una función que sume dos números.
    //
    //TDD Ciclo:
    //
    //Paso 1: Escribir el test (Red)

    @Test
    public void testSumar() {
        Sumador sumador = new Sumador();
        int resultado = sumador.sumar(3, 2);
        assertEquals(5, resultado);
    }

    //Aquí, Sumador es una clase que aún no existe y sumar() es el método que queremos implementar.
    //
    //Paso 2: Implementar el código mínimo (Green)

    public class Sumador {
        public int sumar(int a, int b) {
            return a + b;
        }
    }
}
