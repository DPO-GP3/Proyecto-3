package interfaade;

import javax.swing.*;
import java.awt.*;
import Controller.Controller;

public class VentanaPrincipal extends JFrame {
    private Controller controller;
    private String rolUsuario;

    public VentanaPrincipal(Controller controller, String rolUsuario) {
        this.controller = controller;
        this.rolUsuario = rolUsuario;
        initUI();
    }

    private void initUI() {
        setSize(750, 600);
        setTitle("Sistema de Subastas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        PanelSur panelS = new PanelSur();
        panelS.setBackground(new Color(50, 70, 80)); // Light gray example
        add(panelS, BorderLayout.SOUTH);

        PanelNorte panelN = new PanelNorte();
        panelN.setBackground(new Color(50, 70, 80)); // Light gray example
        add(panelN, BorderLayout.NORTH);

        PanelEste panelE = new PanelEste();
        panelE.setBackground(new Color(230, 230, 230)); // Light gray example
        add(panelE, BorderLayout.EAST);

        PanelIzq panelW = new PanelIzq();
        panelW.setBackground(new Color(230, 230, 230)); // Light gray example
        add(panelW, BorderLayout.WEST);

        JPanel panelC;
        switch (rolUsuario) {
            case "Administrador":
                panelC = new PanelAdministrador(controller);
                break;
            case "Operador":
                panelC = new PanelOperador(controller);
                break;
            case "Cliente":
                panelC = new PanelCliente(controller);
                break;
            default:
                panelC = new JPanel();
                panelC.add(new JLabel("Rol no reconocido"));
        }
        panelC.setBackground(new Color(230, 230, 230)); // Light gray example
        add(panelC, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow(controller);
            loginWindow.setVisible(true);
        });
    }
}