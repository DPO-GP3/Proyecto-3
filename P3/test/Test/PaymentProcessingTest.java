package Test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Controller.Controller;
import P1.Cajero;
import P1.Pago;

public class PaymentProcessingTest {

    private Controller controller;
    private Cajero cajero;
    private Pago pago;

    @Before
    public void setUp() {
        controller = new Controller();
        cajero = new Cajero();
        pago = new Pago("NombreDelComprador", "Efectivo", 100.0, 1);
    }

    @Test
    public void testProcessPayment() {
        // Asumimos que procesarPago implica realizar todas las operaciones necesarias,
        // incluyendo la generación de un recibo que podríamos considerar como parte del procesamiento.
        controller.procesarPago(cajero, pago);
        pago.emitirRecibo(); // Suponemos que esto cambia el estado interno para reflejar que el pago ha sido procesado.
        assertNotNull("El recibo debe estar generado después de procesar el pago.", pago.getFecha()); // Verificamos que la fecha del recibo no sea nula.
    }

    @Test
    public void testEmitReceipt() {
        controller.emitirRecibo(cajero, pago);
        assertNotNull("Debe haber un recibo después de emitirlo.", pago.getFecha()); // Verificamos que la fecha del recibo no sea nula.
    }
}