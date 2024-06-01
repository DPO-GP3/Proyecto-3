package P1;



public class MetodoPago {
    private String comprador; // El nombre del comprador
    private String metodo; // El método de pago

    // Constructor
    public MetodoPago(String comprador, String metodo) {
        this.comprador = comprador;
        this.metodo = metodo;
    }

    // Getter para el nombre del comprador
    public String getComprador() {
        return comprador;
    }

    // Setter para el nombre del comprador
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    // Getter para el método de pago
    public String getMetodo() {
        return metodo;
    }

    // Setter para el método de pago
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}