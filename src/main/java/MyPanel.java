import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyPanel extends JPanel implements ActionListener {

    JButton mainButton;
    JButton priceButton;

    public MyPanel(){
        // Creating buttons
        mainButton = new JButton();
        priceButton = new JButton();

        // Customizing Buttons
        mainButton.setText("Main");
        mainButton.setFocusable(false);
        mainButton.addActionListener(this);
        mainButton.setForeground(Color.BLUE);

        priceButton.setText("Prices");
        priceButton.setFocusable(false);

        this.add(mainButton);
        this.add(priceButton);

        // Final Panel Setup
        setVisible(true);
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
