package P1;

public class Pintura extends Pieza {
    
	private String color;
    private String tecnica;
    private String estilo;

    // Constructor completo con todos los atributos, incluidos los específicos de Pintura
    public Pintura(String ID, String tipo, String titulo, int anioCreacion, String autor, String dimensiones,
			String materialesDeConstruccion, float peso, boolean necesitaElectricidad, String otrosDetalles,
			String estado, String color, String tecnica, String estilo) {
		super(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion, peso, necesitaElectricidad,
				otrosDetalles, estado);
		this.color = color;
        this.tecnica = tecnica;
        this.estilo = estilo;
	}

    // Implementación de métodos abstractos heredados de Pieza
    @Override
    public void registrarPieza() {
        // Simula el registro de la pintura en una base de datos o sistema de inventario
        System.out.println("Registrando la pintura con ID: " + this.getID() + " y título: " + this.getTitulo());
        // Aquí iría el código para guardar los detalles en la base de datos
    }

    @Override
    public void verificarEstado() {
        // Simula la verificación del estado físico o conservación de la pintura
        System.out.println("Verificando el estado de la pintura: " + this.getTitulo());
        // El código aquí podría incluir una comprobación del estado actual y una actualización del atributo 'estado'
        this.setEstado("Verificado"); // Suponiendo que el estado es una cadena simple
    }

    @Override
	public void aprobar() {
        // Simula la aprobación de la pintura para ser mostrada o vendida
        System.out.println("La pintura con ID: " + this.getID() + " ha sido aprobada.");
        // Aquí podrías cambiar el estado de la pintura a "Aprobada" o realizar cualquier otro proceso necesario
        this.setEstado("Aprobada");
    }

    
	public void rechazar() {
        // Simula el rechazo de la pintura, posiblemente por no cumplir con ciertos criterios
        System.out.println("La pintura con ID: " + this.getID() + " ha sido rechazada.");
        // Puedes establecer el estado a "Rechazada" y tal vez registrar la razón del rechazo
        this.setEstado("Rechazada");
    }

    @Override
    public int getPrecio() {
        // Retorna el precio de la Pintura
        // Aquí deberás definir cómo se calcula el precio de una Pintura
        return 0; // Debe ser reemplazado con la lógica de cálculo del precio
    }

    // Getters y setters para los atributos específicos de Pintura
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}