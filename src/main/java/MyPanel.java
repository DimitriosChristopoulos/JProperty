import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MyPanel extends JPanel implements ActionListener {

    JButton mainButton;
    JButton websiteButton;
    JScrollPane scrollPane;
    ScrollPanel scrollPanel;

    public MyPanel(){
        // Creating buttons
        mainButton = new JButton();
        websiteButton = new JButton();

        // Customizing Buttons

        mainButton.setText("Main Menu");
        mainButton.setFocusable(false);
        mainButton.addActionListener(this);
        mainButton.setForeground(Color.BLUE);
        mainButton.setBounds(60,30,300,30);

        websiteButton.setText("Open Website");
        websiteButton.setFocusable(false);
        websiteButton.setForeground(Color.BLUE);
        websiteButton.setBounds(850,500,150,30);

        this.setLayout(null);

        // Adding scroll pane
        scrollPanel = new ScrollPanel();
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setLocation(60, 90);
        scrollPane.setSize(300,420);
        this.add(scrollPane);

        //Adding Buttons
        this.add(mainButton);
        this.add(websiteButton);

        // Final Panel Setup
        setVisible(true);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0x63b9ff));
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(8));
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
