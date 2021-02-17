import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    // Constructor
    Frame(){
        this.setTitle("Cipher App");
        this.setSize(320, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    public static void main(String[] args) {
        new Frame();
    }
}