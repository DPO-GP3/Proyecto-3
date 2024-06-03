package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller;
import P1.Comprador;
import P1.Escultura;
import P1.Pago;
import P1.Subasta;
import P1.Pieza;
import P1.Pintura;

import java.util.ArrayList;
import java.util.List;

public class PanelCliente extends JPanel {
    private Controller controller;
    private Comprador comprador;
    private JTextArea textArea;

    public PanelCliente(Controller controller) {
        this.controller = controller;
        this.comprador = new Comprador("NombreComprador", 1000.0, 500.0); // Crea un comprador por defecto
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnRealizarOferta = new JButton("Realizar Oferta");
        btnRealizarOferta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOferta();
            }
        });
        panelBotones.add(btnRealizarOferta, gbc);

        JButton btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });
        panelBotones.add(btnRealizarCompra, gbc);

        JButton btnRealizarPagoConTarjeta = new JButton("Realizar Pago con Tarjeta");
        btnRealizarPagoConTarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPagoConTarjeta();
            }
        });
        panelBotones.add(btnRealizarPagoConTarjeta, gbc);

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void realizarOferta() {
        // Diálogo para obtener los datos necesarios
        String subastaId = JOptionPane.showInputDialog(this, "Ingrese el ID de la subasta:");
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto de la oferta:");

        try {
            double monto = Double.parseDouble(montoStr);
            Subasta subasta = new Subasta(); // Crear una nueva subasta como ejemplo
            controller.realizarOferta(comprador, subasta, monto);
            textArea.append("Oferta realizada de " + monto + " en la subasta " + subastaId + "\n");
        } catch (NumberFormatException ex) {
            textArea.append("Error: el monto debe ser un número.\n");
        }
    }

    private void realizarCompra() {
        List<Pieza> piezas = getPiezasParaComprar();
        controller.realizarCompra(comprador, piezas);
        for (Pieza pieza : piezas) {
            textArea.append("Intentando comprar: " + pieza.getTitulo() + "\n");
        }
    }

    private void realizarPagoConTarjeta() {
        // Diálogo para obtener los datos necesarios
        String tipoPasarela = JOptionPane.showInputDialog(this, "Ingrese el tipo de pasarela (PayPal, PayU):");
        if (tipoPasarela == null || tipoPasarela.isEmpty()) {
            textArea.append("Error: No se ingresó el tipo de pasarela.\n");
            return;
        }

        String numeroTarjeta = JOptionPane.showInputDialog(this, "Ingrese el número de tarjeta:");
        if (numeroTarjeta == null || numeroTarjeta.isEmpty()) {
            textArea.append("Error: No se ingresó el número de tarjeta.\n");
            return;
        }

        String cvv = JOptionPane.showInputDialog(this, "Ingrese el CVV:");
        if (cvv == null || cvv.isEmpty()) {
            textArea.append("Error: No se ingresó el CVV.\n");
            return;
        }

        String fechaExpiracion = JOptionPane.showInputDialog(this, "Ingrese la fecha de expiración (MM/AA):");
        if (fechaExpiracion == null || fechaExpiracion.isEmpty() || !fechaExpiracion.matches("\\d{2}/\\d{2}")) {
            textArea.append("Error: La fecha de expiración no se ingresó o es inválida. Debe estar en formato MM/AA.\n");
            return;
        }

        // Crear un objeto Pago de ejemplo
        Pago pago = new Pago(comprador.getNombre(), "Tarjeta de crédito", 100.0, 1);

        boolean resultado = controller.procesarPagoConTarjeta(pago, tipoPasarela, numeroTarjeta, cvv, fechaExpiracion);

        if (resultado) {
            textArea.append("Pago procesado exitosamente con " + tipoPasarela + "\n");
        } else {
            textArea.append("Error al procesar el pago con " + tipoPasarela + "\n");
        }
    }


    private List<Pieza> getPiezasParaComprar() {
        // Aquí debes implementar cómo obtendrás la lista de piezas para comprar.
        // Este es un ejemplo básico:
        List<Pieza> piezas = new ArrayList<>();
        piezas.add(new Pintura("P001", "Pintura", "La Noche Estrellada", 1889, "Vincent van Gogh", "73.7 cm x 92.1 cm", "Óleo sobre lienzo", 2.5f, false, "Expuesto en el MoMA", "En exhibición", "Azul y amarillo", "Impresionismo", "Postimpresionista"));
        piezas.add(new Escultura("E001", "Escultura", "David", 1504, "Michelangelo Buonarroti", "17 ft x 7 ft", "Mármol", 5575, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        piezas.add(new Pintura("P002", "Pintura", "El Sueño de una Tarde Dominical en la Alameda Central", 1947, "Diego Rivera", "183 cm x 250 cm", "Óleo sobre tela", 3.2f, false, "Expuesto en el Museo de Arte Moderno de México", "En exhibición", "Varios", "Muralismo", "Mexicano"));
        piezas.add(new Escultura("E002", "Escultura", "El Pensador", 1902, "Auguste Rodin", "71.5 cm x 38 cm x 54.7 cm", "Bronce", 1623, false, "Ubicada en el Museo Rodin de París", "En exhibición", "Bronce patinado"));
        piezas.add(new Pintura("P003", "Pintura", "La Persistencia de la Memoria", 1931, "Salvador Dalí", "24 cm x 33 cm", "Óleo sobre lienzo", 1.0f, false, "Expuesto en el Museo de Arte Moderno de Nueva York", "En exhibición", "Varios", "Surrealismo", "Español"));
        piezas.add(new Escultura("E003", "Escultura", "El David de Miguel Ángel", 1501, "Miguel Ángel", "17 ft", "Mármol", 4782, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        piezas.add(new Pintura("P004", "Pintura", "La Gioconda", 1503, "Leonardo da Vinci", "77 cm x 53 cm", "Óleo sobre tabla de álamo", 2.0f, false, "Expuesto en el Museo del Louvre", "En exhibición", "Varios", "Renacimiento", "Italiano"));
        piezas.add(new Pintura("P005", "Pintura", "La Persistencia de la Memoria", 1931, "Salvador Dalí", "24 cm x 33 cm", "Óleo sobre lienzo", 1.0f, false, "Expuesto en el Museo de Arte Moderno de Nueva York", "En exhibición", "Varios", "Surrealismo", "Español"));
        piezas.add(new Escultura("E005", "Escultura", "El Pensador", 1902, "Auguste Rodin", "71.5 cm x 38 cm x 54.7 cm", "Bronce", 1623, false, "Ubicada en el Museo Rodin de París", "En exhibición", "Bronce patinado"));
        piezas.add(new Pintura("P006", "Pintura", "La Noche Estrellada", 1889, "Vincent van Gogh", "73.7 cm x 92.1 cm", "Óleo sobre lienzo", 2.5f, false, "Expuesto en el MoMA", "En exhibición", "Azul y amarillo", "Impresionismo", "Postimpresionista"));
        piezas.add(new Escultura("E006", "Escultura", "David", 1504, "Michelangelo Buonarroti", "17 ft x 7 ft", "Mármol", 5575, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        piezas.add(new Pintura("P007", "Pintura", "La Ronda de Noche", 1642, "Rembrandt", "363 cm x 437 cm", "Óleo sobre lienzo", 6.5f, false, "Expuesto en el Rijksmuseum de Ámsterdam", "En exhibición", "Varios", "Barroco", "Holandés"));
        piezas.add(new Escultura("E007", "Escultura", "El Pensador", 1902, "Auguste Rodin", "71.5 cm x 38 cm x 54.7 cm", "Bronce", 1623, false, "Ubicada en el Museo Rodin de París", "En exhibición", "Bronce patinado"));
        piezas.add(new Pintura("P008", "Pintura", "Guernica", 1937, "Pablo Picasso", "349 cm x 776 cm", "Óleo sobre lienzo", 8.5f, false, "Expuesto en el Museo Reina Sofía de Madrid", "En exhibición", "Blanco y negro", "Cubismo", "Español"));
        piezas.add(new Escultura("E008", "Escultura", "El Pensador", 1902, "Auguste Rodin", "71.5 cm x 38 cm x 54.7 cm", "Bronce", 1623, false, "Ubicada en el Museo Rodin de París", "En exhibición", "Bronce patinado"));
        piezas.add(new Pintura("P009", "Pintura", "La Joven de la Perla", 1665, "Johannes Vermeer", "46.5 cm x 40 cm", "Óleo sobre lienzo", 1.2f, false, "Expuesto en el Mauritshuis de La Haya", "En exhibición", "Varios", "Barroco", "Holandés"));
        piezas.add(new Escultura("E009", "Escultura", "El David de Miguel Ángel", 1501, "Miguel Ángel", "17 ft", "Mármol", 4782, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
        piezas.add(new Pintura("P010", "Pintura", "La Persistencia de la Memoria", 1931, "Salvador Dalí", "24 cm x 33 cm", "Óleo sobre lienzo", 1.0f, false, "Expuesto en el Museo de Arte Moderno de Nueva York", "En exhibición", "Varios", "Surrealismo", "Español"));
        piezas.add(new Escultura("E011", "Escultura", "La Victoria de Samotracia", 190 , "Desconocido", "244 cm x 183 cm x 91 cm", "Mármol", 2678, false, "Ubicada en el Museo del Louvre", "En exhibición", "Blanco"));
        piezas.add(new Pintura("P012", "Pintura", "La persistencia de la memoria", 1931, "Salvador Dalí", "24 cm x 33 cm", "Óleo sobre lienzo", 1.0f, false, "Expuesto en el Museo de Arte Moderno de Nueva York", "En exhibición", "Varios", "Surrealismo", "Español"));
        piezas.add(new Pintura("P013", "Pintura", "La Última Cena", 1495, "Leonardo da Vinci", "460 cm × 880 cm", "Temple y óleo sobre yeso", 7.0f, false, "Expuesto en Santa María delle Grazie, Milán", "En exhibición", "Varios", "Renacimiento", "Italiano"));
        piezas.add(new Escultura("E013", "Escultura", "David", 1504, "Michelangelo Buonarroti", "17 ft x 7 ft", "Mármol", 5575, false, "Ubicada en la Galería de la Academia de Florencia", "En exhibición", "Blanco"));
       return piezas;
    }
}
