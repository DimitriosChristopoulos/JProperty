import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JPanel {

    JButton testButton;

    public ScrollPanel()
    {
        // Creating Buttons
        testButton = new JButton();

        this.setSize(250,1000);
        this.setPreferredSize(new Dimension(250,1000));
        this.setBackground(Color.red);

        this.setLayout(null);

        // Customizing Buttons
        testButton.setBounds(20,20,250,30);
        testButton.setText("Test");
        testButton.setFocusable(false);

        // Adding Buttons
        this.add(testButton);


        // Final Panel Setup
        setVisible(true);
    }
}
