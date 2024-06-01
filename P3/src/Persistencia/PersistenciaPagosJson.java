package Persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import P1.Pago;
import P1.Subasta;

public class PersistenciaPagosJson implements IPersistenciaPagos {

    @Override
    public void cargarPagos(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException {
        String jsonCompleto = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONArray pagosArray = new JSONArray(jsonCompleto);

        List<Pago> pagos = new ArrayList<>();
        for (int i = 0; i < pagosArray.length(); i++) {
            JSONObject pagoJson = pagosArray.getJSONObject(i);
            pagos.add(parsePago(pagoJson));
        }

        casaSubastas.setPagos(pagos);
    }

    @Override
    public void salvarPagos(String archivo, Subasta casaSubastas) throws IOException {
        JSONArray pagosArray = new JSONArray();

        for (Pago pago : casaSubastas.getPagos()) {
            pagosArray.put(formatPago(pago));
        }

        Files.write(Paths.get(archivo), pagosArray.toString(2).getBytes());
    }

    private Pago parsePago(JSONObject pagoJson) {
        String comprador = pagoJson.getString("comprador");
        String metodoPago = pagoJson.getString("metodoPago");
        double monto = pagoJson.getDouble("monto");
        int cuotas = pagoJson.getInt("cuotas");
        // Puedes agregar más atributos si es necesario
        return new Pago(comprador, metodoPago, monto, cuotas);
    }

    private JSONObject formatPago(Pago pago) {
        JSONObject pagoJson = new JSONObject();
        pagoJson.put("comprador", pago.getComprador());
        pagoJson.put("metodoPago", pago.getMetodoPago());
        pagoJson.put("monto", pago.getMonto());
        pagoJson.put("cuotas", pago.getCuotas());
        // Puedes agregar más atributos si es necesario
        return pagoJson;
    }
}
