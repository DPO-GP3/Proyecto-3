package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller;
import P1.Administrador;
import P1.Pieza;
import P1.Pintura;
import P1.Escultura;

import java.util.ArrayList;
import java.util.List;

public class PanelAdministrador extends JPanel {
    private Controller controller;
    private Administrador administrador;
    private JTextArea textArea;

    public PanelAdministrador(Controller controller) {
        this.controller = controller;
        this.administrador = new Administrador("Admin", "Administrador"); // Crea un administrador por defecto
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre los botones

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnSupervisar = new JButton("Supervisar operación completa");
        btnSupervisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supervisarOperacionCompleta();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panelBotones.add(btnSupervisar, gbc);

        JButton btnAprobarPiezas = new JButton("Aprobar inclusión de piezas");
        btnAprobarPiezas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aprobarInclusionPiezas();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBotones.add(btnAprobarPiezas, gbc);

        JButton btnAdministrarCuentas = new JButton("Administrar cuentas de usuarios");
        btnAdministrarCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administrarCuentasUsuarios();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelBotones.add(btnAdministrarCuentas, gbc);

        JButton btnDecisionesEstrategicas = new JButton("Tomar decisiones estratégicas");
        btnDecisionesEstrategicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarDecisionesEstrategicas();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelBotones.add(btnDecisionesEstrategicas, gbc);

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void supervisarOperacionCompleta() {
        textArea.append("Supervisando la operación completa de la galería y casa de subastas...\n");
        textArea.append("Revisando registros de transacciones...\n");
        textArea.append("Revisando la disponibilidad de nuevas piezas...\n");
        textArea.append("Aprobando o rechazando inclusiones de piezas...\n");
        textArea.append("Tomando decisiones estratégicas...\n");
        textArea.append("Generando informes y estadísticas...\n");
        textArea.append("Operación de supervisión completa.\n");
    }

    private void aprobarInclusionPiezas() {
        List<Pieza> piezas = getPiezasParaAprobar();
        for (Pieza pieza : piezas) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea aprobar la inclusión de la pieza: " + pieza.getTitulo() + " en el inventario?", "Aprobar Piezas", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                pieza.aprobar();
                textArea.append("La pieza " + pieza.getTitulo() + " ha sido aprobada y añadida al inventario.\n");
            } else {
                pieza.rechazar();
                textArea.append("La pieza " + pieza.getTitulo() + " ha sido rechazada.\n");
            }
        }
    }

    private void administrarCuentasUsuarios() {
        textArea.append("Administrando cuentas de usuarios y configuraciones del sistema...\n");
    }

    private void tomarDecisionesEstrategicas() {
        textArea.append("Tomando decisiones estratégicas como precios base y condiciones de consignación...\n");
    }

    private List<Pieza> getPiezasParaAprobar() {
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

