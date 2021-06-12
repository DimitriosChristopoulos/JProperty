import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyPanel extends JPanel implements ActionListener {

    JButton mainButton;
    JButton priceButton;
    ScrollPanel scrollPanel;

    public MyPanel(){
        // Creating buttons
        mainButton = new JButton();
        priceButton = new JButton();

        // Customizing Buttons

        mainButton.setText("Main Menu");
        mainButton.setFocusable(false);
        mainButton.addActionListener(this);
        mainButton.setForeground(Color.BLUE);
        mainButton.setBounds(60,30,300,30);

        priceButton.setText("Prices");
        priceButton.setFocusable(false);

        this.setLayout(null);

        // Adding scroll panel
        scrollPanel = new ScrollPanel();
        this.add(scrollPanel);
        //Adding Buttons
        this.add(mainButton);
        this.add(priceButton);

        // Final Panel Setup
        setVisible(true);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0x63b9ff));
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(60, 90, 300, 420);
        g2.setStroke(oldStroke);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == mainButton)
        {
            System.out.println("Hi");
        }
    }

}
