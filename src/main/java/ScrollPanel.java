import javax.swing.*;

public class ScrollPanel extends JPanel {

    JPanel holder = new JPanel();

    public ScrollPanel()
    {
        holder.setLocation(60, 30);
        holder.setSize(300,420);

        this.setLayout(null);

        // Final Panel Setup
        setVisible(true);
    }


}
