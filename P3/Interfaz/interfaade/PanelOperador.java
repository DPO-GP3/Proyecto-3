package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.Controller;
import P1.Pieza;
import P1.Pintura;
import P1.Subasta;
import P1.Comprador;
import P1.Escultura;
import P1.Operador;

public class PanelOperador extends JPanel {
    private Controller controller;
    private Operador operador;
    private JTextArea textArea;

    public PanelOperador(Controller controller) {
        this.controller = controller;
        this.operador = new Operador("NombreOperador", "RolOperador", new ArrayList<>()); // Crear un operador por defecto
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

        JButton btnIniciarSubasta = new JButton("Iniciar Subasta");
        btnIniciarSubasta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSubasta();
            }
        });
        panelBotones.add(btnIniciarSubasta, gbc);

        JButton btnFinalizarSubasta = new JButton("Finalizar Subasta");
        btnFinalizarSubasta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarSubasta();
            }
        });
        panelBotones.add(btnFinalizarSubasta, gbc);

        JButton btnRegistrarOferta = new JButton("Registrar Oferta");
        btnRegistrarOferta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarOferta();
            }
        });
        panelBotones.add(btnRegistrarOferta, gbc);

        JButton btnAsistirCompradores = new JButton("Asistir Compradores");
        btnAsistirCompradores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asistirCompradores();
            }
        });
        panelBotones.add(btnAsistirCompradores, gbc);

        JButton btnAdministrarSubastas = new JButton("Administrar Subastas");
        btnAdministrarSubastas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administrarSubastas();
            }
        });
        panelBotones.add(btnAdministrarSubastas, gbc);

        JButton btnRealizarRegistro = new JButton("Realizar Registro");
        btnRealizarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRegistro();
            }
        });
        panelBotones.add(btnRealizarRegistro, gbc);

      
      

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void iniciarSubasta() {
        controller.iniciarSubasta(new Subasta());
        textArea.append("Iniciando una nueva subasta...\n");
    }

    private void finalizarSubasta() {
        controller.finalizarSubasta(new Subasta());
        textArea.append("Finalizando la subasta...\n");
    }

    private void registrarOferta() {
        // Diálogo para obtener los datos necesarios
        String compradorNombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del comprador:");
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto de la oferta:");

        try {
            double monto = Double.parseDouble(montoStr);
            Comprador comprador = new Comprador(compradorNombre, 1000.0, 500.0); // Valores de ejemplo
            controller.registrarOferta(new Subasta(), comprador, (float) monto);
            textArea.append("Registrando oferta de " + monto + " para " + compradorNombre + "\n");
        } catch (NumberFormatException ex) {
            textArea.append("Error: el monto debe ser un número.\n");
        }
    }

    private void asistirCompradores() {
        String compradorNombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del comprador:");
        Comprador comprador = new Comprador(compradorNombre, 1000.0, 500.0); // Valores de ejemplo
        controller.asistirCompradores(operador, comprador);
        textArea.append("Asistiendo al comprador: " + compradorNombre + "\n");
    }

    private void administrarSubastas() {
        controller.administrarSubastas(operador);
        textArea.append("Administrando subastas...\n");
    }

    private void realizarRegistro() {
        // Diálogo para obtener los datos necesarios para registrar una pieza
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la pieza:");
        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de la pieza (Pintura/Escultura):");
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título de la pieza:");
        String añoStr = JOptionPane.showInputDialog(this, "Ingrese el año de la pieza:");
        String autor = JOptionPane.showInputDialog(this, "Ingrese el autor de la pieza:");
        String dimensiones = JOptionPane.showInputDialog(this, "Ingrese las dimensiones de la pieza:");
        String material = JOptionPane.showInputDialog(this, "Ingrese el material de la pieza:");
        String pesoStr = JOptionPane.showInputDialog(this, "Ingrese el peso de la pieza:");
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la pieza:");
        String estado = JOptionPane.showInputDialog(this, "Ingrese el estado de la pieza:");

        try {
            int año = Integer.parseInt(añoStr);
            float peso = Float.parseFloat(pesoStr);
            // Crear la pieza según el tipo y registrar en el controller
            Pieza pieza;
            if (tipo.equalsIgnoreCase("Pintura")) {
                pieza = new Pintura(id, tipo, titulo, año, autor, dimensiones, material, peso, false, descripcion, estado, "Colores", "Estilo", "Nacionalidad");
            } else if (tipo.equalsIgnoreCase("Escultura")) {
                pieza = new Escultura(id, tipo, titulo, año, autor, dimensiones, material, peso, false, descripcion, estado, "Color");
            } else {
                textArea.append("Error: Tipo de pieza no reconocido.\n");
                return;
            }
            controller.registrarPieza(pieza);
            textArea.append("Pieza registrada: " + titulo + "\n");
        } catch (NumberFormatException ex) {
            textArea.append("Error: el año y el peso deben ser números.\n");
        }
    }

 
}

