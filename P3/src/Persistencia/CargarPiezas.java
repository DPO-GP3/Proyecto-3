package Persistencia;

import com.google.gson.Gson;

import P1.Pieza;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CargarPiezas {
    public static Pieza[] desdeJSON(String rutaArchivo) {
        try (Reader reader = new FileReader(rutaArchivo)) {
            // Convertir el archivo JSON a un arreglo de objetos Pieza usando Gson
            Gson gson = new Gson();
            Pieza[] piezas = gson.fromJson(reader, Pieza[].class);
            return piezas;
        } catch (IOException e) {
            System.err.println("Error al cargar piezas desde el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}

