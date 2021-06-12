import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JPanel {

    JPanel holder = new JPanel();

    public ScrollPanel()
    {
        holder.setLocation(60, 30);
        holder.setSize(300,420);
        holder.setBackground(Color.red);

        this.setLayout(null);

        //Adding to panel
        this.add(holder);

        // Final Panel Setup
        setVisible(true);
    }
}
