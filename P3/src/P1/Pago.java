package P1;

import java.util.Date;

public class Pago {
    private String metodoPago;
    private double monto;
    private int cuotas;
    private Date fecha;
    private String comprador; // Agregado para almacenar el nombre del comprador

    public Pago(String comprador, String metodoPago, double monto, int cuotas) {
        this.comprador = comprador; // Guarda el nombre del comprador
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.cuotas = cuotas;
        this.fecha = new Date(); // Establece la fecha actual
    }
    public void emitirRecibo() {
        // Lógica para generar un recibo con los detalles del pago
        System.out.println("Recibo generado:");
        System.out.println("Fecha: " + getFecha());
        System.out.println("Método de pago: " + getMetodoPago());
        System.out.println("Monto: " + getMonto());
        System.out.println("Cuotas: " + getCuotas());
        // Aquí puedes añadir más información al recibo si es necesario
    }

    public void registrarPago() {
        // Lógica para registrar el pago en la base de datos o en un sistema externo
        System.out.println("Pago registrado exitosamente.");
    }

    public boolean verificarPago() {
        // Lógica para verificar si el pago se ha realizado correctamente
        return true;
    }
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public void generarRecibo() {
        // Lógica para generar un recibo con los detalles del pago
        System.out.println("Recibo generado:");
        System.out.println("Fecha: " + fecha);
        System.out.println("Método de pago: " + metodoPago);
        System.out.println("Monto: " + monto);
        System.out.println("Cuotas: " + cuotas);
    }

    // Getters y setters
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}