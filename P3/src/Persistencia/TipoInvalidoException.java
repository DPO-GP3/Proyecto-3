package Persistencia;

public class TipoInvalidoException extends Exception
{
    public TipoInvalidoException( String tipoArchivo )
    {
        super( "La cadena '" + tipoArchivo + "' no corresponde a un tipo válido de archivo para la persistencia" );
    }

}
