
package P1;

import java.util.List;

public class Operador extends Empleado {

    private List<Subasta> subastasActivas; // Lista de subastas activas en las que el operador puede trabajar.
    
    public Operador(String nombre, String rol, List<Subasta> subastasActivas) {
        super(nombre, rol);
        this.subastasActivas = subastasActivas;
    }
    
    // Administra las subastas que están en curso, verificando su estado y realizando tareas relacionadas.
    public void administraSubastas() {
        System.out.println("Administrando subastas...");
        for (Subasta subasta : subastasActivas) {
            // Suponiendo que la clase Subasta tiene un método estaEnCurso()
            if (subasta.estaEnCurso()) {
                System.out.println("Subasta en curso: " + subasta.toString());
            } else {
                System.out.println("Subasta finalizada o aún no ha comenzado: " + subasta.toString());
            }
        }
        // Más lógica para administrar subastas podría incluir actualizar el estado de la subasta, notificar a los participantes, etc.
    }
    
    // Registra ofertas en subastas activas.
    public void registraOfertas(Subasta subasta, Oferta oferta) {
        if (subastasActivas.contains(subasta)) {
            Comprador comprador = oferta.getComprador();
            double monto = oferta.getMonto();
            subasta.registrarOferta(comprador, monto);
            System.out.println("Oferta registrada en la subasta.");
        } else {
            System.out.println("La subasta especificada no está siendo manejada por este operador.");
        }
    }
    
    public void realizarTareas() {
        // Implementación específica para Operador
        System.out.println("El operador está realizando sus tareas específicas.");
        // Puedes agregar aquí la lógica relevante para un Operador
    }
    
    
    // Asiste a compradores con información y ayuda durante el proceso de subasta.
    public void asisteCompradores(Comprador comprador) {
        // Suponiendo que Comprador tiene un método para obtener su nombre.
        System.out.println("Asistiendo al comprador: " + comprador.getNombre());
        // La ayuda real podría incluir proporcionar información sobre las subastas actuales, cómo hacer una oferta, o los detalles de las piezas disponibles.
    }
    
    

	
}