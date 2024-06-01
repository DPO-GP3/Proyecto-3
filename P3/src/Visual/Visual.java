package Visual;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Controller.Controller;
import P1.*;

public class Visual {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();

        boolean inicioSesionExitoso = controller.iniciarSesion(nombreUsuario, contrasena);
        String rolUsuario = controller.getRolUsuario(nombreUsuario);  // Suponemos que existe este método

        if (inicioSesionExitoso) {
            System.out.println("Inicio de sesión exitoso. Bienvenido " + rolUsuario);
            mostrarMenu(controller, rolUsuario);
        } else {
            System.out.println("Inicio de sesión fallido. Verifique las credenciales e inténtelo de nuevo.");
        }
        scanner.close();
    }

    private static void mostrarMenu(Controller controller, String rolUsuario) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            if (rolUsuario.equals("Administrador")) {
                System.out.println("1. Supervisar operación completa");
                System.out.println("2. Aprobar inclusión de piezas");
                System.out.println("3. Administrar cuentas de usuarios");
                System.out.println("4. Tomar decisiones estratégicas");
                System.out.println("5. Salir");
            } else if (rolUsuario.equals("Operador")) {
                System.out.println("1. Iniciar subasta");
                System.out.println("2. Finalizar subasta");
                System.out.println("3. Registrar oferta");
                System.out.println("4. Asistir compradores");
                System.out.println("5. Administrar subastas");
                System.out.println("6. Salir");
            } else if (rolUsuario.equals("Cliente")) {
                System.out.println("1. Realizar oferta");
                System.out.println("2. Realizar compra");
                System.out.println("3. Salir");
            }

            int opcion = scanner.nextInt();
            handleUserChoice(opcion, controller, rolUsuario);
        }
        scanner.close();
    }

    private static void handleUserChoice(int opcion, Controller controller, String rolUsuario) {
        switch (rolUsuario) {
            case "Administrador":
                switch (opcion) {
                    case 1: controller.supervisarOperacionCompleta(new Administrador("NombreAdministrador", "RolAdministrador")); break;
                    case 2: controller.aprobarInclusionPiezas(new Administrador("NombreAdministrador", "RolAdministrador"), getPiezas()); break;
                    case 3: controller.administrarCuentasUsuarios(new Administrador("NombreAdministrador", "RolAdministrador")); break;
                    case 4: controller.tomarDecisionesEstrategicas(new Administrador("NombreAdministrador", "RolAdministrador")); break;
                    case 5: System.out.println("Saliendo del sistema..."); System.exit(0); break;
                }
                break;
            case "Operador":
                switch (opcion) {
                    case 1: controller.iniciarSubasta(new Subasta()); break;
                    case 2: controller.finalizarSubasta(new Subasta()); break;
                    case 3: controller.registrarOferta(new Subasta(), new Comprador("NombreComprador", 1000.0, 500.0), 100.0f); break;
                    case 4: controller.asistirCompradores(new Operador("NombreOperador", "RolOperador", new ArrayList<>() ), new Comprador("NombreComprador", 1000.0, 500.0));
                    case 5: controller.administrarSubastas(new Operador("NombreOperador", "RolOperador", new ArrayList<>())); break;
                    case 6: System.out.println("Saliendo del sistema..."); System.exit(0); break;
                }
                break;
            case "Cliente":
                switch (opcion) {
                    case 1: controller.realizarOferta(new Comprador("NombreComprador", 1000.0, 500.0), new Subasta(), 300.0f); break;
                    case 2: controller.realizarCompra(new Comprador("NombreComprador", 1000.0, 500.0), getPiezasParaComprar()); break;
                    case 3: System.out.println("Saliendo del sistema..."); System.exit(0); break;
                }
                break;
        }
    }

    private static List<Pieza> getPiezas() {
        List<Pieza> piezas = new ArrayList<>();
        piezas.add(new Pintura("P001", "Pintura", "La Noche Estrellada", 1889, "Vincent van Gogh", "73.7 cm x 92.1 cm", "Óleo sobre lienzo", 2.5f, false, "Expuesto en el MoMA", "En exhibición", "Azul y amarillo", "Impresionismo", "Postimpresionista"));
        piezas.add(new Video("V001", "Video", "Documental de la Naturaleza", 2020, "Jane Doe", "45 minutos", "Digital", 0, false, "Disponible en 4K", "En archivo", "3840x2160", "Documental"));
        piezas.add(new Escultura("E001", "Escultura", "David", 1504, "Michelangelo Buonarroti", "17 ft x 7 ft", "Mármol", 5575, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        piezas.add(new Fotografia("F001", "Fotografía", "Atardecer en la ciudad", 2022, "Alex Martinez", "640x480", "Papel fotográfico", 0.5f, false, "Foto impresa en alta calidad", "En archivo", "Alta resolución", "Color"));
        return piezas;
    }

    private static List<Pieza> getPiezasParaComprar() {
        List<Pieza> piezasParaComprar = new ArrayList<>();
        piezasParaComprar.add(new Pintura("P002", "Pintura", "El Grito", 1893, "Edvard Munch", "91 cm x 73.5 cm", "Óleo, tempera, pastel y lápiz sobre cartón", 1.0f, true, "Expuesto en el Museo Nacional de Arte, Arquitectura y Diseño", "En exhibición", "Rojo y azul", "Expresionismo", "Postimpresionista"));
        piezasParaComprar.add(new Escultura("E001", "Escultura", "David", 1504, "Michelangelo Buonarroti", "17 ft x 7 ft", "Mármol", 5575, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        return piezasParaComprar;
    }
}