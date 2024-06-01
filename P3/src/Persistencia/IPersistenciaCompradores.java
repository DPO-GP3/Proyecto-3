package Persistencia;

import java.io.IOException;

import P1.Subasta;

public interface IPersistenciaCompradores {

	void cargarCompradores(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException;

	void salvarCompradores(String archivo, Subasta casaSubastas) throws IOException;

}
