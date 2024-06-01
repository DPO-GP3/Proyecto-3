
package Test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import Controller.Controller;


class AuthenticationTest {

    private Controller controller;

    @Before
    void setUp() {
        controller = new Controller();
        // Inicializando usuarios de prueba con roles específicos
        controller.agregarUsuarioValido("admin", "adminpass", "administrador");
        controller.agregarUsuarioValido("operador", "operpass", "operador");
        controller.agregarUsuarioValido("cliente", "clientepass", "cliente");
    }

    @Test
    public void testLoginSuccess() {
        assertTrue("La autenticación debería ser exitosa con credenciales correctas", 
                   controller.iniciarSesion("admin", "adminpass"));
    }

    @Test
    public void testLoginFailure() {
        assertFalse("La autenticación debe fallar con contraseña incorrecta", 
                    controller.iniciarSesion("admin", "incorrect"));
    }
}
