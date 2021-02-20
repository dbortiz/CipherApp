import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainTest {
    private static SelectFilePanel selectFile;
    private static SelectCipherPanel selectCipher;
    private static SelectKeyPanel selectKey;
    private static EncryptOrDecryptPanel encryptOrDecrypt;
    private static JLabel errorLabel;
    
    public static void main(String[] args) {
        JFrame frame = new Frame();
       
        // Panel components
        selectFile = new SelectFilePanel(frame);
        selectCipher = new SelectCipherPanel();
        selectKey = new SelectKeyPanel();
        encryptOrDecrypt = new EncryptOrDecryptPanel();
        JButton button = new JButton("Submit");
        errorLabel = new JLabel();

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        frame.getContentPane().add(selectFile, 0);
        frame.getContentPane().add(selectCipher, 1);
        frame.getContentPane().add(selectKey, 2);
        frame.getContentPane().add(encryptOrDecrypt, 3);
        frame.add(button, 4);
        frame.add(errorLabel, 5);

        // Action listener for 'submit' button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cipher = selectCipher.getCipher();
                String key = selectKey.getKey();
                String file = selectFile.getFile();

                if (fileChecker(file) && keyChecker(cipher, key)){
                    Cipher c;
                    String mode = encryptOrDecrypt.getMode();
                    
                    // Performs selected mode on selected file with selected cipher using selected key
                    c = selectCipher(cipher, key, mode);
                    if (mode.equals("Encrypt")) {
                        c.encrypt(file);
                    } else {
                        c.decrypt(file);
                    }
                    errorLabel.setText("Success!");
                }
            }
        });

        frame.setPreferredSize(new Dimension(450,300));
        frame.pack();
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
                selected = new Hill(key, mode);
            */
            case "Monoalphabetic":
                selected = new Monoalphabetic(key, mode);
                break;
            case "Playfair":
                selected = new Playfair(key, mode);
                break;
            /*
            case "Rail Fence":
                selected = new RailFence(key, mode);
            case "Row Transposition":
                selected = new RowTransposition(key, mode);
            case "Vigenere":
                selected = new Vigenere(key, mode);
            */
            default:
                System.out.println("oops in here");
        }

        return selected;
    }

    // Private method to check if the key for the selected cipher is appropriate
    private static Boolean keyChecker(String cipher, String key) {
        if (key.length() == 0) {
            errorLabel.setText("Please use a whole number greater than 0.");
            return false;
        }

        if (cipher.equals("Caesar") || cipher.equals("Row Transposition") || cipher.equals("Rail Fence")) {
            for (char c : key.toCharArray()) {
                if (!Character.isDigit(c) || Character.getNumericValue(c) < 1) {
                    errorLabel.setText("Please use a whole number greater than 0.");
                    return false;
                }
            }
            return true;
        } else {
            for (char c : key.toCharArray()) {
                if (!Character.isLetter(c)) {
                    errorLabel.setText("Please use a key of letters with no spaces, digits, or special characters.");
                    return false;
                }
            }
            return true;
        }
    }

    // Check to see if a file has been chosen
    private static Boolean fileChecker(String fileName) {
        if (fileName == null) {
            errorLabel.setText("Please select a text file.");
            return false;
        }
        return true;
    }
}