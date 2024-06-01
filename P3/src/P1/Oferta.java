package P1;

public class Oferta {
    private Comprador comprador;
    private double monto;

    // Constructor
    public Oferta(Comprador comprador, double monto) {
        this.comprador = comprador;
        this.monto = monto;
    }
 // Getters para acceder a las propiedades de Oferta si son necesarios
    public Comprador getComprador() {
        return comprador;
    }

    public double getMonto() {
        return monto;
    }

    // ... cualquier otro método que necesites en tu clase Oferta
}