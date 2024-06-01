package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import P1.Subasta;
import Controller.Controller;

public class AuctionManagementTest {

    private Controller controller;
    private Subasta subasta;

    @Before
    public void setUp() {
        controller = new Controller();
        subasta = new Subasta(); // Creando una nueva instancia de Subasta para cada test
    }

    @Test
    public void testStartAuction() {
        controller.iniciarSubasta(subasta);
        assertTrue("La subasta debería estar activa después de iniciarla.", subasta.estaEnCurso());
    }

    @Test
    public void testEndAuction() {
        controller.iniciarSubasta(subasta); // Aseguramos que la subasta está iniciada primero
        controller.finalizarSubasta(subasta);
        assertFalse("La subasta debería estar inactiva después de finalizarla.", subasta.estaEnCurso());
    }
}
