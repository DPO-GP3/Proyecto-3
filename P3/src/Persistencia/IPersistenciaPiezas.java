package Persistencia;

import java.io.IOException;

import P1.Subasta;

public interface IPersistenciaPiezas {

    void cargarPiezas(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException;

    void salvarPiezas(String archivo, Subasta casaSubastas) throws IOException;

}
