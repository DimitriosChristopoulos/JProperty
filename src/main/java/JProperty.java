import org.bson.Document;

import javax.swing.*;

public class JProperty extends JFrame {
    // Frame Object
    MyPanel frame;
    // Constructor
    public JProperty(){
        super("JProperty"); // Setting the title
        // Constructing frame
        frame = new MyPanel();
        // Initializing Networking Features
        NetworkHandler.init();
        // Setting window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,576);
        setResizable(false);
        setLocationRelativeTo(null);
        add(frame);
        setVisible(true);
    }
    public static void main(String[] args){
        System.out.println("JProperty!");
        JProperty mainInstance = new JProperty();
        // listings test
        for(Document listing: NetworkHandler.getListings(5)){
            System.out.println(listing.get("name"));
        }
    }
}
