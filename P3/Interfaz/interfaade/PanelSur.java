package interfaade;

import javax.swing.*;
import java.awt.*;

public class PanelSur extends JPanel {
    public PanelSur() {
        setLayout(new GridLayout(1, 2));

        JTextField inputField = new JTextField();
        JButton actionButton = new JButton("Ejecutar Acción");

        add(inputField);
        add(actionButton);
    }
}
