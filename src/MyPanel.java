import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel{

    JPanel redPanel = new JPanel();
    JButton button;
    MyPanel(JProperty frame){
        // Creating buttons
        button = new JButton();

        redPanel.setBackground(Color.red);
        redPanel.setBounds(0,0,250,250);

        JProperty.frame.add(redPanel);
    }

}
