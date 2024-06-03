package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller;

public class LoginWindow extends JFrame {
    private Controller controller;

    public LoginWindow(Controller controller) {
        this.controller = controller;
        setTitle("Inicio de Sesión");

        // Set preferred size for better scaling
        setPreferredSize(new Dimension(450, 250));

        // Use a BorderLayout for background image and centered content
        setLayout(new BorderLayout());

        // Add background image (replace "background.jpg" with your image path)
        JLabel backgroundLabel = new JLabel(new ImageIcon("./datos/imagenes/fondo.jpg"));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        // Create a transparent panel for login elements
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        loginPanel.setOpaque(false); // Make panel transparent

        // Username label with custom font
        JLabel userLabel = new JLabel("Nombre de Usuario:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Username text field with border
        JTextField userField = new JTextField(20);
        userField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Password label with custom font
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Password field with border
        JPasswordField passField = new JPasswordField(20);
        passField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Login button with custom colors and hover effect
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBackground(new Color(0, 128, 0)); // Green color
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                boolean authenticated = controller.iniciarSesion(username, password);
                if (authenticated) {
                    String role = controller.getRolUsuario(username);
                    VentanaPrincipal mainWindow = new VentanaPrincipal(controller, role);
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 177, 76)); // Darker green on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 128, 0)); // Reset color on exit
            }
        });

        // Add login elements to the transparent panel
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginButton);

        // Add transparent panel to the background label
        backgroundLabel.add(loginPanel, BorderLayout.CENTER);

        pack(); // Adjust window size to fit components
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow(controller).setVisible(true);
            }
        });
    }
}
