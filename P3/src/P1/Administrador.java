package P1;

import java.util.List;
import java.util.Scanner;

public class Administrador extends Empleado {

    public Administrador(String nombre, String rol) {
        super(nombre, rol);
    }

    public void supervisarOperacionCompleta() {
        System.out.println("Supervisando la operación completa de la galería y casa de subastas...");
        System.out.println("Revisando registros de transacciones...");
        System.out.println("Revisando la disponibilidad de nuevas piezas...");
        System.out.println("Aprobando o rechazando inclusiones de piezas...");
        System.out.println("Tomando decisiones estratégicas...");
        System.out.println("Generando informes y estadísticas...");
        System.out.println("Operación de supervisión completa.");
    }

    public void aprobarInclusionPiezas(List<Pieza> piezas) {
        try (Scanner scanner = new Scanner(System.in)) {
            for (Pieza pieza : piezas) {
                System.out.println("¿Desea aprobar la inclusión de la pieza: " + pieza.getTitulo() + " en el inventario? (S/N)");
                String respuesta = scanner.nextLine();

                if (respuesta.equalsIgnoreCase("S")) {
                    pieza.aprobar();
                    System.out.println("La pieza " + pieza.getTitulo() + " ha sido aprobada y añadida al inventario.");
                } else if (respuesta.equalsIgnoreCase("N")) {
                    pieza.rechazar();
                    System.out.println("La pieza " + pieza.getTitulo() + " ha sido rechazada.");
                } else {
                    System.out.println("Respuesta no válida. Por favor, responda 'S' para aprobar o 'N' para rechazar.");
                }
            }
        }
    }
    
    public void realizarTareas() {
        // Implementación específica para Administrador
        System.out.println("El administrador está supervisando todas las operaciones.");
        // Puedes agregar aquí la lógica relevante para un Administrador
    }



    public void administrarCuentasUsuarios() {
        System.out.println("Administrando cuentas de usuarios y configuraciones del sistema...");
    }

    public void tomarDecisionesEstrategicas() {
        System.out.println("Tomando decisiones estratégicas como precios base y condiciones de consignación...");
    }
}