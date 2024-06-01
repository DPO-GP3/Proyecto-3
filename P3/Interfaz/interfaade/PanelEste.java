package interfaade;

import javax.swing.*;

import Controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEste extends JPanel {
    public PanelEste() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Estirar horizontalmente

        // Título del panel
        JLabel titleLabel = new JLabel("Configuración", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.PAGE_START; // Anclar al inicio de la página
        add(titleLabel, gbc);

        // Espacio vertical entre el título y el primer botón
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Cerrar Programa
        JButton btnCerrarPrograma = new JButton("Cerrar Programa");
        btnCerrarPrograma.setPreferredSize(new Dimension(150, 50)); // Tamaño preferido
        btnCerrarPrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo del sistema...");
                System.exit(0);
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0; // No estirar verticalmente
        add(btnCerrarPrograma, gbc);

        // Espacio vertical entre los botones
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Volver a Autenticación
        JButton btnAutenticacion = new JButton("Volver a Autenticación");
        btnAutenticacion.setPreferredSize(new Dimension(150, 50));
        btnAutenticacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelEste.this);
                frame.dispose(); // Cierra la ventana principal
                Controller controller = new Controller();
                LoginWindow loginWindow = new LoginWindow(controller);
                loginWindow.setVisible(true);
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0;
        add(btnAutenticacion, gbc);

        // Espacio vertical entre los botones
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Ayuda
        JButton btnAyuda = new JButton("Ayuda");
        btnAyuda.setPreferredSize(new Dimension(150, 50));
        btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Para asistencia, por favor contacte al soporte técnico.", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0;
        add(btnAyuda, gbc);

        // Espacio vertical entre el último botón y el final del panel
        gbc.gridy++;
        gbc.weighty = 1.0; // Estirar verticalmente el espacio vacío
        add(Box.createVerticalGlue(), gbc);
    }
}
