import org.bson.Document;
import org.bson.types.Decimal128;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MyPanel extends JPanel implements ActionListener {
    // Current properties shown
    ArrayList<Document> currentProperties = new ArrayList<>();
    int selectedProperty = -1;
    // Panel related things
    JButton mainButton;
    JButton websiteButton;
    JButton mapButton;
    JScrollPane scrollPane;
    ScrollPanel scrollPanel;

    // Labels for the text area

    JLabel name;

    public MyPanel(){

        this.setLayout(null);

        // Setting background color
        this.setBackground(new Color(0xa3d5ff));

        // Creating buttons
        mainButton = new JButton();
        websiteButton = new JButton();
        mapButton = new JButton();

        // Creating labels
        name = new JLabel("Property Name");

        // Customizing Buttons

        mainButton.setText("Search Listings");
        mainButton.setFocusable(false);
        mainButton.addActionListener(this);
        mainButton.setBackground(new Color(59, 89, 182));
        mainButton.setForeground(Color.WHITE);
        mainButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        mainButton.setBounds(60,30,300,30);

        websiteButton.setText("Open Website");
        websiteButton.setFocusable(false);
        websiteButton.setForeground(Color.BLUE);
        websiteButton.setBackground(new Color(59, 89, 182));
        websiteButton.setForeground(Color.WHITE);
        websiteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        websiteButton.setBounds(850,500,150,30);

        mapButton.setText("Open Heat Map");
        mapButton.setFocusable(false);
        mapButton.addActionListener(this);
        mapButton.setBackground(new Color(59, 89, 182));
        mapButton.setForeground(Color.WHITE);
        mapButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        mapButton.setBounds(850,30,150,30);

        // Setting Labels

        name.setLocation(440,90);
        name.setSize(700,40);
        name.setFont(new Font ("Tahoma", Font.BOLD, 24));

        // Adding scroll pane
        scrollPanel = new ScrollPanel(this);
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setLocation(60, 90);
        scrollPane.setSize(300,420);
        this.add(scrollPane);

        // Adding Buttons
        this.add(mainButton);
        this.add(websiteButton);
        this.add(mapButton);

        // Adding Labels

        this.add(name);

        // Final Panel Setup
        setVisible(true);
    }

    public void selectProperty(int index){
        selectedProperty = index;
        name.setText("<html>" + currentProperties.get(selectedProperty).get("name") + "</html>");
    }

    public void airbnbPopulator(String country, int maxPrice){
        currentProperties.clear();
        for(Document listing: NetworkHandler.getListings(1000)) {
            Document addressInfo  = (Document) listing.get("address");
            if(addressInfo.get("country").equals(country) || country.equals("Worldwide")){
                if(((Decimal128) listing.get("price")).doubleValue() < maxPrice || maxPrice == 0){
                    currentProperties.add(listing);
                }
            }
        }
        updateScrollPanel();
    }

    public void updateScrollPanel(){
        scrollPanel.updateButtons(currentProperties);

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
            // Search tool
            int selection = JOptionPane.showOptionDialog(this,
                    "Which database would you like to access?",
                    "Database Choice",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Airbnb", "Local listings"},"none");
            //Airbnb selection
            if(selection == 0){
                System.out.println("airbnb");
                String country = (String)JOptionPane.showInputDialog(
                        this,
                        "Select the country you would like to search in",
                        "Country selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[]{"Worldwide", "United States", "Spain", "Australia", "Canada", "Turkey", "Portugal", "Brazil", "Hong Kong"},
                        "Worldwide");

                String maxPriceString = (String)JOptionPane.showInputDialog(
                        this,
                        "What is the maximum price per day you are looking for? (Write 0 for no limit)",
                        "Country selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "0");
                int maxPrice;
                try{
                    maxPrice = Integer.parseInt(maxPriceString);
                    airbnbPopulator(country, maxPrice);
                    if(currentProperties.size() == 0){
                        JOptionPane.showMessageDialog(this, "No properties found matching your search. (Try a higher max price?)");
                    }
                }
                catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(this, "Invalid price. (Must be integer)");
                }

            }
            // Local houses selection
            else if (selection == 1){
                System.out.println("houses");
            }
        }
        else if (e.getSource() == mapButton)
        {
            //this.add()
        }
    }

}
