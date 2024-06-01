package Persistencia;

public class CentralPersistencia {
    
    public static final String JSON = "JSON";
    public static final String PLAIN = "PlainText";

    
    public static PersistenciaPiezasJson getPersistenciaPiezas(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equalsIgnoreCase(tipoArchivo)) {
            return new PersistenciaPiezasJson();
        } else {
            throw new TipoInvalidoException("Tipo de archivo no válido: " + tipoArchivo);
        }
    }


    
    public static IPersistenciaCompradores getPersistenciaCompradores(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equalsIgnoreCase(tipoArchivo)) {
            return new PersistenciaCompradoresJson();
        } else {
            throw new TipoInvalidoException("Tipo de archivo no válido: " + tipoArchivo);
        }
    }

    
    public static IPersistenciaPagos getPersistenciaPagos(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equalsIgnoreCase(tipoArchivo)) {
            return new PersistenciaPagosJson();
        } else {
            throw new TipoInvalidoException("Tipo de archivo no válido: " + tipoArchivo);
        }
    }
}
