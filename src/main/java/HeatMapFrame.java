import javax.swing.*;

public class HeatMapFrame extends JFrame {

    HeatMapPanel mapFrame;

    public HeatMapFrame()
    {
        super("Heat Map"); // Setting the title
        // Constructing frame
        mapFrame = new HeatMapPanel();
        // Initializing Networking Features
        NetworkHandler.init();
        // Setting window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,576);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mapFrame);
        setVisible(true);
    }

}
