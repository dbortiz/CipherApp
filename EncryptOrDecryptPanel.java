import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EncryptOrDecryptPanel extends JPanel{
    private String mode;

    EncryptOrDecryptPanel() {
        // Creates layout for panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Components for panel
        JLabel label = new JLabel("Encrypt/Decrypt: ");
        JRadioButton encrypt = new JRadioButton("Encrypt", true);
        JRadioButton decrypt = new JRadioButton("Decrypt");
        ButtonGroup selection = new ButtonGroup();

        // Add components to button group
        selection.add(encrypt);
        selection.add(decrypt);

        // Implement ActionListener for each individual button
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton button = (AbstractButton) e.getSource();
                mode = button.getText();
            }
        };

        // Add action listeners to individual buttons
        encrypt.addActionListener(listener);
        decrypt.addActionListener(listener);

        // Add button group to panel
        this.add(label);
        this.add(encrypt);
        this.add(decrypt);
    }

    public String getMode() {
        return mode;
    }
}