package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class PanelIzq extends JPanel {

    private JLabel labelImagen;
    private JButton botonAtras, botonAdelante;
    private JFileChooser fileChooser;
    private String[] arregloImagenes;
    private int contador = 0;
    private String contenidoDirectorio;

    public PanelIzq() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Título del panel
        JLabel titleLabel = new JLabel("Obras de Arte", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Panel de imagen
        labelImagen = new JLabel();
        JScrollPane scroll = new JScrollPane(labelImagen);

        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createTitledBorder("Visualización de la imagen"));
        panelImagen.add(scroll, BorderLayout.CENTER);
        add(panelImagen, BorderLayout.CENTER);

        // Botones de navegación
        botonAtras = new JButton("Anterior");
        botonAdelante = new JButton("Siguiente");

        JPanel panelComponentes = new JPanel(new FlowLayout());
        panelComponentes.add(botonAtras);
        panelComponentes.add(botonAdelante);
        panelComponentes.setBorder(BorderFactory.createTitledBorder("Controles de imagen"));
        add(panelComponentes, BorderLayout.SOUTH);

        // Configuración del FileChooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Acción para seleccionar directorio
        JButton btnSeleccionarDirectorio = new JButton("Seleccionar Directorio");
        btnSeleccionarDirectorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarDirectorio();
            }
        });
        panelComponentes.add(btnSeleccionarDirectorio);

        // Acción del botón Anterior
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarImagenAnterior();
            }
        });

        // Acción del botón Siguiente
        botonAdelante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarImagenSiguiente();
            }
        });
    }

    private void seleccionarDirectorio() {
        if (fileChooser.showDialog(null, "Abrir imagen") == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = fileChooser.getSelectedFile();
            contenidoDirectorio = archivoElegido.getAbsolutePath();

            File direccion = new File(contenidoDirectorio);
            if (direccion.isDirectory()) {
                contador = 0;
                arregloImagenes = direccion.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        String fileName = name.toLowerCase();
                        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
                                || fileName.endsWith(".gif") || fileName.endsWith(".png");
                    }
                });
                if (arregloImagenes != null && arregloImagenes.length > 0) {
                    mostrarImagen(contador);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron imágenes en el directorio seleccionado.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void mostrarImagen(int indice) {
        if (arregloImagenes != null && contenidoDirectorio != null) {
            String path = contenidoDirectorio + File.separator + arregloImagenes[indice];
            ImageIcon iconito = new ImageIcon(path);
            labelImagen.setIcon(iconito);
        }
    }

    private void mostrarImagenAnterior() {
        if (arregloImagenes != null && contenidoDirectorio != null) {
            contador--;
            if (contador < 0) {
                contador = arregloImagenes.length - 1;
            }
            mostrarImagen(contador);
        }
    }

    private void mostrarImagenSiguiente() {
        if (arregloImagenes != null && contenidoDirectorio != null) {
            contador++;
            if (contador >= arregloImagenes.length) {
                contador = 0;
            }
            mostrarImagen(contador);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Galería de Obras de Arte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.add(new PanelIzq());
        frame.setVisible(true);
    }
}
