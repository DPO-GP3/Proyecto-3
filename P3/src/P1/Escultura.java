package P1;

import java.util.HashMap;
import java.util.Map;

public class Escultura extends Pieza {

    private String material;
    private float peso;

    public Escultura(String ID, String tipo, String titulo, int anioCreacion, String autor, String dimensiones,
                     String materialesDeConstruccion, float peso, boolean necesitaElectricidad,
                     String otrosDetalles, String estado, String material) {
        super(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion, peso,
              necesitaElectricidad, otrosDetalles, estado);
        this.material = material;
        this.peso = peso;
    }

    @Override
    public void registrarPieza() {
        // Implementación de registro en la base de datos
        // Suponiendo que existe una clase Database para gestionar las operaciones de base de datos
        
        System.out.println("Escultura " + this.getTitulo() + " registrada en la base de datos.");
    }

    @Override
    public void verificarEstado() {
        if (conservacionEsAceptable()) {
            this.setEstado("Verificado");
            System.out.println("Estado de la escultura " + this.getTitulo() + " verificado y es aceptable.");
        } else {
            this.setEstado("Requiere restauración");
            System.out.println("La escultura " + this.getTitulo() + " requiere atención y restauración.");
        }
    }


    @Override
	public void aprobar() {
        // Lógica de aprobación
        if (this.getEstado().equals("Verificado") || this.getEstado().equals("Bueno")) {
            this.setEstado("Aprobada");
            System.out.println("Escultura aprobada para exposición/venta.");
        }
    }

    @Override
	public void rechazar() {
        // Lógica de rechazo
        this.setEstado("Rechazada");
        System.out.println("Escultura rechazada para exposición/venta.");
    }

    @Override
    public int getPrecio() {
        return calcularPrecioBasadoEnFactores();
    }

    private int calcularPrecioBasadoEnFactores() {
        int basePrice = 100; // Precio base para el cálculo
        float materialCoeficiente = obtenerCoeficienteMaterial(this.material);
        float pesoCoeficiente = obtenerCoeficientePeso(this.peso);
        
        int precio = (int) (basePrice * materialCoeficiente * pesoCoeficiente);
        return precio;
    }

    private float obtenerCoeficienteMaterial(String material) {
        // Lógica para obtener coeficiente basado en el material
        // Ejemplo: El mármol podría tener un coeficiente mayor que el yeso
        return material.equalsIgnoreCase("mármol") ? 1.5f : 1.0f;
    }

    private float obtenerCoeficientePeso(float peso) {
        // Lógica para obtener coeficiente basado en el peso
        // Ejemplo: Una escultura más pesada podría ser más valiosa
        return peso > 50.0f ? 1.2f : 1.0f;
    }

    private boolean conservacionEsAceptable() {
        // Aquí incluirías la lógica para determinar si la conservación es aceptable
        return true; // Por defecto, asumimos que es aceptable
    }

    private Map<String, Object> toMap() {
        // Convierte la información de Escultura en un mapa para la base de datos
        Map<String, Object> map = new HashMap<>();
        map.put("ID", this.getID());
        map.put("tipo", this.getTipo());
        map.put("titulo", this.getTitulo());
        map.put("anioCreacion", this.getAnioCreacion());
        map.put("autor", this.getAutor());
        map.put("dimensiones", this.getDimensiones());
        map.put("materialesDeConstruccion", this.getMaterialesDeConstruccion());
        map.put("peso", this.getPeso());
        map.put("necesitaElectricidad", this.getNecesitaElectricidad());
        map.put("otrosDetalles", this.getOtrosDetalles());
        map.put("estado", this.getEstado());
        // Si tienes atributos específicos de Escultura como 'material', también deberías agregarlos.
        map.put("material", this.getMaterial());
        // Continúa agregando otros atributos que sean necesarios.
        return map;}


    // Getters y setters para los atributos específicos de Escultura
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }}