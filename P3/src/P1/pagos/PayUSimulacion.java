package P1.pagos;

import P1.Pago;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class PayUSimulacion implements PasarelaDePago {
    @Override
    public boolean procesarPago(Pago pago, String numeroTarjeta, String cvv, String fechaExpiracion) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            dateFormat.setLenient(false);
            Date fechaExp = dateFormat.parse(fechaExpiracion);
            Date fechaActual = new Date();

            // Verificar que la tarjeta comience con un número específico para simular una validación
            if (!numeroTarjeta.startsWith("4")) {
                System.out.println("Pago con PayU rechazado para tarjeta " + numeroTarjeta + " (no comienza con '4')");
                return false;
            }

            // Comprobar si la fecha de expiración es posterior a la fecha actual
            if (fechaExp.after(fechaActual)) {
                System.out.println("Pago con PayU procesado para tarjeta " + numeroTarjeta);
                return true;
            } else {
                System.out.println("Pago con PayU rechazado para tarjeta " + numeroTarjeta + " (fecha de expiración inválida)");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido: " + fechaExpiracion);
            return false;
        }
    }
}