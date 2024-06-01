package Persistencia;

import P1.Pieza;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "piezas.json";
        Pieza[] piezas = CargarPiezas.desdeJSON(rutaArchivo);

        if (piezas != null) {
            // Imprimir las piezas cargadas desde el archivo JSON
            for (Pieza pieza : piezas) {
                System.out.println(pieza);
            }
        }
    }
    }
