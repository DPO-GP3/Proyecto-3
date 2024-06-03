package Persistencia;



import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import P1.Subasta;
import P1.Video;
import P1.Escultura;
import P1.Fotografia;
import P1.Pieza;
import P1.Pintura;




public class PersistenciaPiezasJson implements IPersistenciaPiezas {

    public void cargarPiezas(String archivo, Subasta casaSubastas) throws IOException, TipoInvalidoException {
        String jsonCompleto = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONArray piezasArray = new JSONArray(jsonCompleto);

        List<Pieza> piezas = new ArrayList<>();
        for (int i = 0; i < piezasArray.length(); i++) {
            JSONObject piezaJson = piezasArray.getJSONObject(i);
            piezas.add(parsePieza(piezaJson));
        }

        casaSubastas.setPiezas(piezas);
    }

    public void salvarPiezas(String archivo, Subasta casaSubastas) throws IOException {
        JSONArray piezasArray = new JSONArray();

        for (Pieza pieza : casaSubastas.getPiezas()) {
            piezasArray.put(formatPieza(pieza));
        }

        Files.write(Paths.get(archivo), piezasArray.toString(2).getBytes());
    }

    private Pieza parsePieza(JSONObject piezaJson) {
        String ID = piezaJson.getString("ID");
        String tipo = piezaJson.getString("tipo");
        String titulo = piezaJson.getString("titulo");
        int anioCreacion = piezaJson.getInt("anioCreacion");
        String autor = piezaJson.getString("autor");
        String dimensiones = piezaJson.getString("dimensiones");
        String materialesDeConstruccion = piezaJson.getString("materialesDeConstruccion");
        float peso = (float) piezaJson.getDouble("peso");
        boolean necesitaElectricidad = piezaJson.getBoolean("necesitaElectricidad");
        String otrosDetalles = piezaJson.getString("otrosDetalles");
        String estado = piezaJson.getString("estado");

        // Dependiendo del tipo de pieza, crea una instancia de la clase concreta correspondiente
        if ("Pintura".equalsIgnoreCase(tipo)) {
            // Obtén los atributos específicos de Pintura
            String color = piezaJson.getString("color");
            String tecnica = piezaJson.getString("tecnica");
            String estilo = piezaJson.getString("estilo");
            // Crea una instancia de Pintura
            return new Pintura(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion,
                                peso, necesitaElectricidad, otrosDetalles, estado, color, tecnica, estilo);
        } else if ("Escultura".equalsIgnoreCase(tipo)) {
            // Obtén los atributos específicos de Escultura
            String dimensionesEscultura = piezaJson.getString("dimensionesEscultura");
            String material = piezaJson.getString("material");
            // Crea una instancia de Escultura
            return new Escultura(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion,
                                peso, necesitaElectricidad, otrosDetalles, estado, dimensionesEscultura);
        } else if ("Video".equalsIgnoreCase(tipo)) {
            // Obtén los atributos específicos de Video
            String duracion = piezaJson.getString("duracion");
            String resolucion = piezaJson.getString("resolucion");
            // Crea una instancia de Video
            return new Video(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion,
                            peso, necesitaElectricidad, otrosDetalles, estado, duracion, resolucion);
        } else if ("Fotografia".equalsIgnoreCase(tipo)) {
            // Obtén los atributos específicos de Fotografia
            String resolucionFoto = piezaJson.getString("resolucionFoto");
            String tipoColor = piezaJson.getString("tipoColor");
            // Crea una instancia de Fotografia
            return new Fotografia(ID, tipo, titulo, anioCreacion, autor, dimensiones, materialesDeConstruccion,
                                peso, necesitaElectricidad, otrosDetalles, estado, resolucionFoto, tipoColor);
        } else {
            
            return null;
        }
    }
    
    

    private JSONObject formatPieza(Pieza pieza) {
        JSONObject piezaJson = new JSONObject();
        piezaJson.put("ID", pieza.getID());
        piezaJson.put("tipo", pieza.getTipo());
        piezaJson.put("titulo", pieza.getTitulo());
        piezaJson.put("anioCreacion", pieza.getAnioCreacion());
        piezaJson.put("autor", pieza.getAutor());
        piezaJson.put("dimensiones", pieza.getDimensiones());
        piezaJson.put("materialesDeConstruccion", pieza.getMaterialesDeConstruccion());
        piezaJson.put("peso", pieza.getPeso());
        piezaJson.put("necesitaElectricidad", pieza.getNecesitaElectricidad());
        piezaJson.put("otrosDetalles", pieza.getOtrosDetalles());
        piezaJson.put("estado", pieza.getEstado());
        return piezaJson;
    }
}

