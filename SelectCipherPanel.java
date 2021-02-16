import javax.swing.*;
import java.awt.*;

public class SelectCipherPanel extends JPanel {
    SelectCipherPanel() {
        // Sets layout for panel
        this.setLayout(new FlowLayout());

        // Available ciphers
        String[] ciphers = {"Caesar", "Hill", "Monoalphabetic", "Playfair", "Railfence", "Row Transposition", "Vigenere"};

        // Components for dropdown list
        final JLabel label = new JLabel("Choose Cipher: ");
        final JComboBox<String> cipherList = new JComboBox<String>(ciphers);

        // Add components to panel
        this.add(label);
        this.add(cipherList);
    }
}