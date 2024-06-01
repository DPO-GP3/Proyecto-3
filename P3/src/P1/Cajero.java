package P1;

import java.util.HashMap;
import java.util.Map;

public class Cajero {
    private Map<String, Double> pagosRegistrados;
    private Map<String, String> metodosDePagoRegistrados; // Se añade para llevar el control de los métodos de pago

    public Cajero() {
        this.pagosRegistrados = new HashMap<>();
        this.metodosDePagoRegistrados = new HashMap<>();
    }

    // Este método debe ser llamado cuando se procesa un pago nuevo
    public void procesarPagos(Pago pago) {
        if (pago != null) {
            String comprador = pago.getComprador();
            double monto = pago.getMonto();
            String metodoDePago = pago.getMetodoPago(); // Asegúrate de que esto coincida con el nombre del método en tu clase Pago

            // Lógica para procesar el pago
            System.out.println("Procesando pago de " + comprador + " por $" + monto + " mediante " + metodoDePago);
            // Aquí podrías realizar operaciones adicionales, como validar el método de pago
            pagosRegistrados.put(comprador, monto);
            metodosDePagoRegistrados.put(comprador, metodoDePago);
            // Asumimos que hay una manera de emitir un recibo en la clase Pago
            pago.emitirRecibo();
        }
    }

    // Este método debería ser llamado para emitir un recibo después de procesar un pago
    public void emitirRecibos(Pago pago) {
        if (pago != null) {
            String comprador = pago.getComprador();
            double monto = pago.getMonto();
            String metodoDePago = pago.getMetodoPago(); // Usa el nombre del método correcto aquí

            System.out.println("Emitiendo recibo para " + comprador + ": $" + monto + " mediante " + metodoDePago);
        }
    }
    // Este método debe ser llamado para registrar un nuevo método de pago
    public void registrarMetodosDePago(MetodoPago metodoPago) {
        if (metodoPago != null) {
            String comprador = metodoPago.getComprador();
            String metodo = metodoPago.getMetodo();

            metodosDePagoRegistrados.put(comprador, metodo);
            System.out.println("Método de pago '" + metodo + "' registrado para " + comprador);
        }
    }
}

