package P1;

public class Fotografia extends Pieza {

    private String resolucion;
    private String tipoDeColor;

    // Constructor de Fotografía que llama al constructor de la superclase Pieza
    public Fotografia(String ID, String tipo, String titulo, int anioCreacion, String autor, String dimensiones,
                      String materialesDeConstruccion, float peso, boolean necesitaElectricidad,
                      String otrosDetalles, String estado, String resolucion, String tipoDeColor) {
        super(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion, peso,
              necesitaElectricidad, otrosDetalles, estado);
        this.resolucion = resolucion;
        this.tipoDeColor = tipoDeColor;
    }

    @Override
    public void registrarPieza() {
        // Lógica para registrar la fotografía en el sistema o base de datos
        System.out.println("Registrando fotografía: " + this.getTitulo());
        // Aquí el código para guardar la información de la fotografía
    }

    @Override
    public void verificarEstado() {
        // Ejemplo simple de verificación basado en la resolución de la fotografía
        if (this.getResolucion().equals("Alta")) {
            this.setEstado("Verificado");
            System.out.println("Estado de la fotografía " + this.getTitulo() + " verificado y es aceptable.");
        } else {
            this.setEstado("Requiere revisión");
            System.out.println("La fotografía " + this.getTitulo() + " requiere revisión para verificación.");
        }
    }

    @Override
	public void aprobar() {
        // Suponemos que una fotografía necesita estar verificada antes de ser aprobada
        if (this.getEstado().equals("Verificado")) {
            this.setEstado("Aprobada");
            System.out.println("Aprobando fotografía: " + this.getTitulo() + ". Ahora está disponible para exposición o venta.");
        } else {
            System.out.println("La fotografía: " + this.getTitulo() + " no puede ser aprobada porque aún no ha sido verificada.");
        }
    }

    @Override
	public void rechazar() {
        // Aquí simplemente cambiamos el estado a "Rechazada" con una notificación adecuada
        this.setEstado("Rechazada");
        System.out.println("Rechazando fotografía: " + this.getTitulo() + ". No está disponible para exposición ni venta.");
    }

    @Override
    public int getPrecio() {
        // Este método llamará a calcularPrecio para obtener el precio basado en diversos factores.
        return calcularPrecio();
    }

    

    // Métodos para calcular el precio de la fotografía
    private int calcularPrecio() {
        int basePrice = 50; // Precio base
        int resolucionBonus = this.getResolucion().equals("Alta") ? 20 : 0;
        int colorBonus = this.getTipoDeColor().equals("Color") ? 30 : 10;
        
        return basePrice + resolucionBonus + colorBonus; // Calcula el precio final
    }

    // Getters y setters para los atributos específicos de Fotografía
    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTipoDeColor() {
        return tipoDeColor;
    }

    public void setTipoDeColor(String tipoDeColor) {
        this.tipoDeColor = tipoDeColor;
    }
}