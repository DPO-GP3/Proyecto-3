package Persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import P1.Comprador;
import P1.Subasta;

public class PersistenciaCompradoresJson implements IPersistenciaCompradores {

    @Override
    public void cargarCompradores(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException {
        String jsonCompleto = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONArray compradoresArray = new JSONArray(jsonCompleto);

        List<Comprador> compradores = new ArrayList<>();
        for (int i = 0; i < compradoresArray.length(); i++) {
            JSONObject compradorJson = compradoresArray.getJSONObject(i);
            compradores.add(parseComprador(compradorJson));
        }

        casaSubastas.setCompradores(compradores);
    }

    @Override
    public void salvarCompradores(String archivo, Subasta casaSubastas) throws IOException {
        JSONArray compradoresArray = new JSONArray();

        for (Comprador comprador : casaSubastas.getCompradores()) {
            compradoresArray.put(formatComprador(comprador));
        }

        Files.write(Paths.get(archivo), compradoresArray.toString(2).getBytes());
    }

    private Comprador parseComprador(JSONObject compradorJson) {
        String nombre = compradorJson.getString("nombre");
        double limiteCompra = compradorJson.getDouble("limiteCompra");
        double saldo = compradorJson.getDouble("saldo");

        Comprador comprador = new Comprador(nombre, limiteCompra, saldo);
        
        // Si tienes ofertas almacenadas en el JSON, aquí puedes recuperarlas y agregarlas al comprador
        
        return comprador;
    }

    private JSONObject formatComprador(Comprador comprador) {
        JSONObject compradorJson = new JSONObject();
        compradorJson.put("nombre", comprador.getNombre());
        compradorJson.put("limiteCompra", comprador.getLimiteCompra());
        compradorJson.put("saldo", comprador.getSaldo());
        // Si necesitas guardar las ofertas realizadas, aquí puedes incluirlas en el JSON
        
        return compradorJson;
    }
}
