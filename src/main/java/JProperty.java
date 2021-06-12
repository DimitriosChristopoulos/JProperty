import javax.swing.*;

public class JProperty extends JFrame {
    // Frame Object
    MyPanel frame;
    // Constructor
    public JProperty(){
        super("JProperty"); // Setting the title
        // Contructing frame
        frame = new MyPanel();
        // Initializing Networking Features
        /*NetworkHandler.init();*/
        // Setting window properties
        setSize(1024,576);
        setResizable(false);
        setLocationRelativeTo(null);
        add(frame);
        setVisible(true);
    }
    public static void main(String[] args){
        System.out.println("JProperty!");
        JProperty mainInstance = new JProperty();
    }
}
