package P1.pagos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import P1.Pago;

public class PaypalSimulacion implements PasarelaDePago {
    @Override
    public boolean procesarPago(Pago pago, String numeroTarjeta, String cvv, String fechaExpiracion) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            Date fechaExp = dateFormat.parse(fechaExpiracion);
            Date fechaActual = new Date();

            if (!numeroTarjeta.startsWith("4")) {
                System.out.println("Pago con PayPal rechazado para tarjeta " + numeroTarjeta + " (no comienza con '4')");
                return false;
            }

            if (fechaExp.after(fechaActual)) {
                System.out.println("Pago con PayPal procesado para tarjeta " + numeroTarjeta);
                return true;
            } else {
                System.out.println("Pago con PayPal rechazado para tarjeta " + numeroTarjeta + " (fecha de expiración inválida)");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido: " + fechaExpiracion);
            return false;
        }
    }
}