package Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import P1.Subasta;
import P1.pagos.PasarelaDePago;
import P1.pagos.PayUSimulacion;
import P1.pagos.PaypalSimulacion;
import P1.Comprador;
import P1.Cajero;
import P1.Pago;
import P1.MetodoPago;
import P1.Operador;
import P1.Oferta;
import P1.Administrador;
import P1.Pieza;
import Persistencia.IPersistenciaPagos;
import Persistencia.IPersistenciaPiezas;
import Persistencia.IPersistenciaCompradores;
import Persistencia.PersistenciaPagosJson;
import Persistencia.PersistenciaPiezasJson;
import Persistencia.TipoInvalidoException;
import Persistencia.PersistenciaCompradoresJson;
import interfaade.visualizations.HeatMapFrame; 

public class Controller {
    private List<Usuario> usuariosValidos = new ArrayList<>();
    private Subasta subasta;
    private IPersistenciaPiezas persistenciaPiezas;
    private IPersistenciaCompradores persistenciaCompradores;
    private IPersistenciaPagos persistenciaPagos;
    private HashMap<String, PasarelaDePago> pasarelasDisponibles = new HashMap<>();

    public Controller() {
        inicializarUsuariosValidos();
        this.subasta = new Subasta();
        this.persistenciaPiezas = new PersistenciaPiezasJson();
        this.persistenciaCompradores = new PersistenciaCompradoresJson();
        this.persistenciaPagos = new PersistenciaPagosJson();
        inicializarPasarelasDePago(); 
    }

    private void inicializarUsuariosValidos() {
        agregarUsuarioValido("admin", "adminpass", "Administrador");
        agregarUsuarioValido("operador", "operpass", "Operador");
        agregarUsuarioValido("cliente", "clientepass", "Cliente");
    }
    
    public void mostrarHeatmap() {
        HeatMapFrame heatMapFrame = new HeatMapFrame("Heat Map de Ventas");
        heatMapFrame.pack();
        heatMapFrame.setVisible(true);
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuariosValidos) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    private static class Usuario {
        private String nombre;
        private String contrasena;
        private String rol;

        public Usuario(String nombre, String contrasena, String rol) {
            this.nombre = nombre;
            this.contrasena = contrasena;
            this.rol = rol;
        }

        public String getNombre() {
            return nombre;
        }

        public String getContrasena() {
            return contrasena;
        }

        public String getRol() {
            return rol;
        }
    }

    public void agregarUsuarioValido(String nombreUsuario, String contrasena, String rol) {
        usuariosValidos.add(new Usuario(nombreUsuario, contrasena, rol));
    }

    public String getRolUsuario(String nombreUsuario) {
        for (Usuario usuario : usuariosValidos) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return usuario.getRol();
            }
        }
        return null;
    }

    public void cargarDatos(String archivoPiezas, String archivoCompradores, String archivoPagos) {
        try {
            persistenciaPiezas.cargarPiezas(archivoPiezas, subasta);
            persistenciaCompradores.cargarCompradores(archivoCompradores, subasta);
            persistenciaPagos.cargarPagos(archivoPagos, subasta);
            System.out.println("Datos cargados exitosamente.");
        } catch (IOException | TipoInvalidoException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void guardarDatos(String archivoPiezas, String archivoCompradores, String archivoPagos) {
        try {
            persistenciaPiezas.salvarPiezas(archivoPiezas, subasta);
            persistenciaCompradores.salvarCompradores(archivoCompradores, subasta);
            persistenciaPagos.salvarPagos(archivoPagos, subasta);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public void iniciarSubasta(Subasta subasta) {
        subasta.iniciarSubasta();
    }

    public void finalizarSubasta(Subasta subasta) {
        subasta.finalizarSubasta();
    }

    public void registrarOferta(Subasta subasta, Comprador comprador, float monto) {
        subasta.registrarOferta(comprador, monto);
    }

    public void procesarPago(Cajero cajero, Pago pago) {
        cajero.procesarPagos(pago);
    }

    public void emitirRecibo(Cajero cajero, Pago pago) {
        cajero.emitirRecibos(pago);
    }

    public void registrarMetodoPago(Cajero cajero, MetodoPago metodoPago) {
        cajero.registrarMetodosDePago(metodoPago);
    }

    public void administrarSubastas(Operador operador) {
        operador.administraSubastas();
    }

    public void registrarPieza(Pieza pieza) {
        pieza.registrarPieza();
    }

    public void registrarOferta(Operador operador, Subasta subasta, Oferta oferta) {
        operador.registraOfertas(subasta, oferta);
    }

    public void asistirCompradores(Operador operador, Comprador comprador) {
        operador.asisteCompradores(comprador);
    }

    public void supervisarOperacionCompleta(Administrador administrador) {
        administrador.supervisarOperacionCompleta();
    }

    public void aprobarInclusionPiezas(Administrador administrador, List<Pieza> piezas) {
        administrador.aprobarInclusionPiezas(piezas);
    }

    public void administrarCuentasUsuarios(Administrador administrador) {
        administrador.administrarCuentasUsuarios();
    }

    public void tomarDecisionesEstrategicas(Administrador administrador) {
        administrador.tomarDecisionesEstrategicas();
    }

    public void realizarOferta(Comprador comprador, Subasta subasta, double monto) {
        comprador.realizarOferta(subasta, monto);
    }

    public void realizarCompra(Comprador comprador, List<Pieza> piezas) {
        comprador.realizarCompra(piezas);
    }

    public void registrarPago(Pago pago) {
        pago.registrarPago();
    }

    public void verificarPago(Pago pago) {
        pago.verificarPago();
    }

    public void generarRecibo(Pago pago) {
        pago.generarRecibo();
    }

    public boolean realizarPago(Pago pago, String tipoPasarela, String numeroTarjeta, String CVV, String fechaExpiracion) {
        PasarelaDePago pasarela = pasarelasDisponibles.get(tipoPasarela);
        if (pasarela != null) {
            return pasarela.procesarPago(pago, numeroTarjeta, CVV, fechaExpiracion);
        }
        return false; // Pasarela no soportada
    }

    public void cargarDatos() {
        try {
            persistenciaPagos.cargarPagos("ruta/al/archivo/de/pagos.json", subasta);
        } catch (IOException | TipoInvalidoException e) {
            System.err.println("Error al cargar los pagos: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        try {
            persistenciaPagos.salvarPagos("ruta/al/archivo/de/pagos.json", subasta);
        } catch (IOException e) {
            System.err.println("Error al guardar los pagos: " + e.getMessage());
        }
    }

    public void cargarConfiguracionPasarelas(String archivoConfig) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(archivoConfig));
        pasarelasDisponibles.clear();
        for (String linea : lineas) {
            switch (linea) {
                case "PayPal":
                    pasarelasDisponibles.put("PayPal", new PaypalSimulacion());
                    break;
                case "PayU":
                    pasarelasDisponibles.put("PayU", new PayUSimulacion());
                    break;
                // Agrega más casos según las pasarelas que desees simular
            }
        }
    }

    public boolean procesarPagoConTarjeta(Pago pago, String tipoPasarela, String numeroTarjeta, String cvv, String fechaExpiracion) {
        // Primero, validaciones básicas de los datos de entrada
        if (numeroTarjeta == null || numeroTarjeta.length() != 16 || !numeroTarjeta.matches("\\d+")) {
            System.err.println("Número de tarjeta inválido.");
            return false;
        }

        if (cvv == null || cvv.length() != 3 || !cvv.matches("\\d+")) {
            System.err.println("CVV inválido.");
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
        format.setLenient(false); // Asegura que la fecha debe ser válida exactamente según el formato especificado
        try {
            Date fecha = format.parse(fechaExpiracion);
            if (fecha.before(new Date())) {
                System.err.println("La fecha de expiración es inválida. La tarjeta está vencida.");
                return false;
            }
        } catch (ParseException e) {
            System.err.println("Formato de fecha de expiración inválido.");
            return false;
        }

        // Aquí se verifica que la pasarela de pago esté disponible y se procesa el pago
        PasarelaDePago pasarela = pasarelasDisponibles.get(tipoPasarela);
        if (pasarela != null) {
            return pasarela.procesarPago(pago, numeroTarjeta, cvv, fechaExpiracion);
        } else {
            System.err.println("Tipo de pasarela no soportado.");
            return false;
        }
    }


    public void registrarTransaccion(Pago pago, boolean resultado) {
        String registro = "Pago " + (resultado ? "aprobado" : "rechazado") + ": " + pago.toString();
        try {
            Files.writeString(Paths.get("transacciones.txt"), registro + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al registrar la transacción: " + e.getMessage());
        }
    }

    public void cargarYProcesarPagos(String archivoPagos) {
        try {
            subasta = new Subasta(); // Asegúrate de que la subasta esté inicializada correctamente
            persistenciaPagos.cargarPagos(archivoPagos, subasta);
            System.out.println("Pagos cargados exitosamente.");

            // Procesar cada pago a través de la pasarela de pago correspondiente
            for (Pago pago : subasta.getPagos()) {
                // Supongamos que tienes estos datos para la simulación
                String numeroTarjeta = "1234567812345678";
                String CVV = "123";
                String fechaExpiracion = "12/24";

                boolean resultado = realizarPago(pago, pago.getMetodoPago(), numeroTarjeta, CVV, fechaExpiracion);
                registrarTransaccion(pago, resultado); // Asume que registrarTransaccion se encarga de registrar si el pago fue exitoso o no
            }
        } catch (IOException | TipoInvalidoException e) {
            System.err.println("Error al cargar y procesar los pagos: " + e.getMessage());
        }
    }
    private void inicializarPasarelasDePago() {
        pasarelasDisponibles.put("PayPal", new PaypalSimulacion());
        pasarelasDisponibles.put("PayU", new PayUSimulacion());
        // Añade otras pasarelas de pago según sea necesario
    }
}
