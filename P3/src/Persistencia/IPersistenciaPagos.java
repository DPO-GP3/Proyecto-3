package Persistencia;

import java.io.IOException;
import P1.Subasta;

public interface IPersistenciaPagos {
    void cargarPagos(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException;
    void salvarPagos(String archivo, Subasta casaSubastas) throws IOException;
}