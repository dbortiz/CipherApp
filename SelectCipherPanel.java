import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectCipherPanel extends JPanel {
    private String selectedItem;

    SelectCipherPanel() {
        // Sets layout for panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Available ciphers
        String[] ciphers = {"Caesar", "Hill", "Monoalphabetic", "Playfair", "Railfence", "Row Transposition", "Vigenere"};

        // Components for dropdown list
        final JLabel label = new JLabel("Choose Cipher: ");
        final JComboBox<String> cipherList = new JComboBox<String>(ciphers);

        cipherList.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                selectedItem = String.valueOf(cipherList.getSelectedItem());
            }
        });
        

        // Add components to panel
        this.add(label);
        this.add(cipherList);
    }

    public String getCipher() {
        return selectedItem;
    }
}