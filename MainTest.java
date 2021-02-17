import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainTest {
    private static SelectFilePanel selectFile;
    private static SelectCipherPanel selectCipher;
    private static SelectKeyPanel selectKey;
    private static EncryptOrDecryptPanel encryptOrDecrypt;

    public static void main(String[] args) {
        JFrame frame = new Frame();
       
        // Panel components
        selectFile = new SelectFilePanel(frame);
        selectCipher = new SelectCipherPanel();
        selectKey = new SelectKeyPanel();
        encryptOrDecrypt = new EncryptOrDecryptPanel();
        JButton button = new JButton("Submit");

        // Add components
        frame.getContentPane().add(selectFile, 0);
        frame.getContentPane().add(selectCipher, 1);
        frame.getContentPane().add(selectKey, 2);
        frame.getContentPane().add(encryptOrDecrypt, 3);
        frame.add(button, 4);

        // Action listener for 'submit' button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cipher c;
                String file = selectFile.getFile();
                String cipher = selectCipher.getCipher();
                String key = selectKey.getKey();
                String mode = encryptOrDecrypt.getMode();
                
                // Performs selected mode on selected file with selected cipher using selected key
                c = selectCipher(cipher, key, mode);
                if (mode.equals("Encrypt")) {
                    c.encrypt(file);
                } else {
                    c.decrypt(file);
                }
            }
        });

        frame.setVisible(true);
    }

    // Private method to choose and initialize cipher for main method
    private static Cipher selectCipher(String cipher, String key, String mode) {
        Cipher selected = null;
        switch(cipher) {
            case "Caesar":
                selected = new Caesar(key, mode);
                break;
            /*
            case "Hill":
                c = new Hill(key, mode);
            case "Monoalphabetic":
                c = new Monoalphabetic(key, mode);
            case "Playfair":
                c = new Playfair(key, mode);
            case "Rail Fence":
                c = new RailFence(key, mode);
            case "Row Transposition":
                c = new RowTransposition(key, mode);
            case "Vigenere":
                c = new Vigenere(key, mode);
            */
            default:
                System.out.println("oops in here");
        }

        return selected;
    }
}