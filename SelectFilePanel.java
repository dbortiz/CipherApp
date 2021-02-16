import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.*;
import java.awt.event.*;

public class SelectFilePanel extends JPanel{
    static File selectedFile;

    public SelectFilePanel(JFrame frame) {
        // Creates layout for panel
        this.setLayout(new FlowLayout());

        // Components
        final JLabel label = new JLabel("Select File: ");
        final JButton button = new JButton("Search");
        JLabel fileSelectedLabel = new JLabel();

        // Action Listener for looking up file when "..." button clicked
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Component for file chooser
                // Configured to only accept text files
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text Documents", "txt"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                
                // Opens directory for file selection and declares file value to option
                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    fileSelectedLabel.setText(selectedFile.getName() + " has been selected.");
                } else {
                    fileSelectedLabel.setText("No file chosen.");
                }
            }
        });

        this.add(label);
        this.add(button);
        this.add(fileSelectedLabel);
    }
}