import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectKeyPanel extends JPanel{
    private String k;
    private JPasswordField key;

    SelectKeyPanel() {
        // Creates layout for panel
        this.setLayout(new FlowLayout());

        // Components for key selection
        final JLabel label = new JLabel("Enter key: ");
        key = new JPasswordField(10);
        key.setEchoChar('*');

        // Resize password viewer
        ImageIcon icon = new ImageIcon("password_icon.png", "Toggable Password Viewer");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        final JLabel view = new JLabel(icon);
        
        // Hovering over icon will allow for user to view currently typed key
        view.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseEntered(MouseEvent e) {
                key.setEchoChar((char)0);
            }
        });

        // While not hovering over icon, key will be defaulted to '*'
        view.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseExited(MouseEvent e) {
                key.setEchoChar('*');
            }
        });

        // Add components to panel
        this.add(label);
        this.add(key);
        this.add(view);
    }

    public String getKey(){
        return new String(key.getPassword());
    }
}