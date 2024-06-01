package Controller;

import java.util.List;
import java.util.ArrayList;
import P1.Subasta;
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
import java.io.IOException;

public class Controller {
    // Lista de usuarios válidos
    private List<Usuario> usuariosValidos = new ArrayList<>();
    private Subasta subasta;
    private IPersistenciaPiezas persistenciaPiezas;
    private IPersistenciaCompradores persistenciaCompradores;
    private IPersistenciaPagos persistenciaPagos;

    // Constructor que inicializa las cuentas predefinidas y las instancias de persistencia
    public Controller() {
        inicializarUsuariosValidos();
        this.subasta = subasta;
        this.persistenciaPiezas = new PersistenciaPiezasJson();
        this.persistenciaCompradores = new PersistenciaCompradoresJson();
        this.persistenciaPagos = new PersistenciaPagosJson();
    }

    // Método para inicializar las credenciales predefinidas
    private void inicializarUsuariosValidos() {
        agregarUsuarioValido("admin", "adminpass", "Administrador");
        agregarUsuarioValido("operador", "operpass", "Operador");
        agregarUsuarioValido("cliente", "clientepass", "Cliente");
    }

    // Método para verificar las credenciales e iniciar sesión
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuariosValidos) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return true; // Inicio de sesión exitoso
            }
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }

    // Método para agregar más usuarios válidos (simulados)
    private static class Usuario {
        private String nombre;
        private String contrasena;
        private String rol;  // Añade esto

        public Usuario(String nombre, String contrasena, String rol) {  // Modificar esto
            this.nombre = nombre;
            this.contrasena = contrasena;
            this.rol = rol;  // Añade esto
        }

        public String getNombre() {
            return nombre;
        }

        public String getContrasena() {
            return contrasena;
        }

        public String getRol() {  // Añade getter para rol
            return rol;
        }
    }

    // Modifica este método para que acepte y maneje el rol
    public void agregarUsuarioValido(String nombreUsuario, String contrasena, String rol) {
        usuariosValidos.add(new Usuario(nombreUsuario, contrasena, rol));
    }

    // Método para obtener el rol de un usuario
    public String getRolUsuario(String nombreUsuario) {
        for (Usuario usuario : usuariosValidos) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return usuario.getRol();
            }
        }
        return null; // Retorna null si el usuario no existe
    }
    // Métodos relacionados con la carga y guardado de datos
    public void cargarDatos(String archivoPiezas, String archivoCompradores, String archivoPagos) {
        try {
            // Cargar datos de piezas, compradores y pagos desde archivos
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
            // Guardar datos de piezas, compradores y pagos en archivos
            persistenciaPiezas.salvarPiezas(archivoPiezas, subasta);
            persistenciaCompradores.salvarCompradores(archivoCompradores, subasta);
            persistenciaPagos.salvarPagos(archivoPagos, subasta);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Otros métodos relacionados con la lógica de la aplicación
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
}

