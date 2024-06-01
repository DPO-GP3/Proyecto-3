package P1;

public class Video extends Pieza {

    private String duracion;  // Duración del video, por ejemplo, "120 minutos"
    private String resolucion;  // Resolución del video, por ejemplo, "1920x1080"

    // Constructor completo que inicializa todos los atributos heredados y los específicos de Video
    public Video(String ID, String tipo, String titulo, int anioCreacion, String autor, String dimensiones,
                 String materialesDeConstruccion, float peso, boolean necesitaElectricidad, String otrosDetalles,
                 String estado, String duracion, String resolucion) {
        super(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion, peso,
              necesitaElectricidad, otrosDetalles, estado);
        this.duracion = duracion;
        this.resolucion = resolucion;
    }

    @Override
    public void registrarPieza() {
        // Aquí podrías insertar los detalles del video en una base de datos o archivo
        System.out.println("Registrando video: " + this.getTitulo() + " en la base de datos.");
        // Implementación de registro en base de datos o archivo
    }

    @Override
    public void verificarEstado() {
        // Verificar si el video es de buena calidad y si cumple con los requisitos para ser exhibido
        System.out.println("Verificando el estado de calidad del video: " + this.getTitulo());
        this.setEstado("Verificado");  // Actualizar estado a "Verificado" si pasa la verificación
    }

    @Override
	public void aprobar() {
        // Aprobar el video para exhibición o venta
        System.out.println("Aprobando el video: " + this.getTitulo());
        this.setEstado("Aprobada");  // Marcar como "Aprobada"
    }

    @Override
	public void rechazar() {
        // Rechazar el video si no cumple con los estándares necesarios
        System.out.println("Rechazando el video: " + this.getTitulo());
        this.setEstado("Rechazada");  // Marcar como "Rechazada"
    }

    @Override
    public int getPrecio() {
        // Calcular el precio del video basado en varios factores como duración y resolución
        return calcularPrecio();
    }

    private int calcularPrecio() {
        // Precio base más un cálculo basado en duración y resolución
        int basePrice = 100;
        int duracionBonus = Integer.parseInt(duracion.split(" ")[0]) > 60 ? 50 : 20;  // Bonus si el video es largo
        int resolutionBonus = resolucion.equals("1920x1080") ? 50 : 10;  // Bonus para alta resolución
        return basePrice + duracionBonus + resolutionBonus;
    }

    // Getters y setters para duración y resolución
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
}