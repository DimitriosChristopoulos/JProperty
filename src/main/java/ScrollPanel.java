import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JPanel {

    public ScrollPanel()
    {

        this.setSize(250,1000);
        this.setPreferredSize(new Dimension(250,1000));
        this.setBackground(Color.red);

        this.setLayout(null);

        JButton testButton = new JButton();
        testButton.setBounds(0,500,250,100);
        testButton.setText("shivan's house");
        this.add(testButton);


        // Final Panel Setup
        setVisible(true);
    }
}
