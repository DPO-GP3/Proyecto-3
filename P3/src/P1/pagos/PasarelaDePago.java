package P1.pagos;
import P1.Pago;

public interface PasarelaDePago {
    boolean procesarPago(Pago pago, String numeroTarjeta, String CVV, String fechaExpiracion);
}