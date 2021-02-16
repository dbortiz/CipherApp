import javax.swing.*;

public class Frame extends JFrame {
    // Instance variable
    JFrame frame;
    
    // Constructor
    Frame(){
        setTitle("Cipher App");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}