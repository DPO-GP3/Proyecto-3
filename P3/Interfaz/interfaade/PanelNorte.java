package interfaade;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNorte extends JPanel
{
    private JLabel lblImg;
    
    public PanelNorte()
    {
        lblImg = new JLabel( );
        add(lblImg);
        
        ImageIcon iconito= new ImageIcon("./datos/imagenes/titulo.png" );
        lblImg.setIcon( iconito );
    }
}
