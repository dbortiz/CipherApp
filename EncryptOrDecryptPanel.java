import javax.swing.*;
import java.awt.*;

public class EncryptOrDecryptPanel extends JPanel{
    EncryptOrDecryptPanel() {
        // Creates layout for panel
        this.setLayout(new FlowLayout());

        // Components for panel
        JLabel label = new JLabel("Encrypt/Decrypt: ");
        JRadioButton encrypt = new JRadioButton("Encrypt");
        JRadioButton decrypt = new JRadioButton("Decrypt");
        ButtonGroup selection = new ButtonGroup();

        // Add components to button group
        selection.add(encrypt);
        selection.add(decrypt);

        // Add button group to panel
        this.add(label);
        this.add(encrypt);
        this.add(decrypt);
    }
}