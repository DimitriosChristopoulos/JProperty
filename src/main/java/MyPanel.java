import jdk.swing.interop.SwingInterOpUtils;
import org.bson.Document;
import org.bson.types.Decimal128;
import java.awt.Desktop;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.multi.MultiButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class MyPanel extends JPanel implements ActionListener {
    // Current properties shown
    ArrayList<Document> currentProperties = new ArrayList<>();
    int selectedProperty = -1;
    Image currentImage = null;
    // Panel related things
    JButton mainButton;
    JButton websiteButton;
    JButton mapButton;
    JScrollPane scrollPane;
    ScrollPanel scrollPanel;
    JTextArea text;
    public MyPanel(){

        this.setLayout(null);

        // Setting background color
        this.setBackground(new Color(0xa3d5ff));

        // Creating buttons
        mainButton = new JButton();
        websiteButton = new JButton();
        mapButton = new JButton();

        //Creating Text Area
        text = new JTextArea("Drugs");

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
        websiteButton.addActionListener(this);

        mapButton.setText("Open Heat Map");
        mapButton.setFocusable(false);
        mapButton.addActionListener(this);
        mapButton.setBackground(new Color(59, 89, 182));
        mapButton.setForeground(Color.WHITE);
        mapButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        mapButton.setBounds(850,30,150,30);

        // Customizing Text Area
        text.setLineWrap(true);
        text.setBounds(400,100,580,380);
        text.setWrapStyleWord(true);
        text.setBackground(new Color(0xfcefef));
        text.setBorder(BorderFactory.createBevelBorder(1));
        text.setFont(new Font ("Tahoma", Font.PLAIN, 14));
        text.setText("\n" + "   Property Name \n\n" + "   Summary: \n" + "   Price: \n" + "   Property Type: \n" );
        text.setEditable(false);

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

        // Adding Text Area
        this.add(text);

        // Final Panel Setup
        setVisible(true);
    }

    public void selectProperty(int index){
        selectedProperty = index;
    }

    public void getPropertyImage(){
        JDialog jDialog = new JDialog();
        jDialog.setLayout(new GridBagLayout());
        jDialog.add(new JLabel("Fetching image..."));
        jDialog.setMinimumSize(new Dimension(150, 50));
        jDialog.setResizable(false);
        jDialog.setModal(false);
        jDialog.setUndecorated(true);
        jDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        jDialog.revalidate();
        jDialog.paintComponents(jDialog.getGraphics());
        try {
            URL url = new URL((String) ((Document)currentProperties.get(selectedProperty).get("images")).get("picture_url"));
            Image image = ImageIO.read(url);
            double lengthRatio = (double) image.getHeight(this)/image.getWidth(this);
            image = image.getScaledInstance(200, (int)(200*lengthRatio), Image.SCALE_DEFAULT);
            currentImage = image;
        } catch (IOException e) {
            System.out.println("Image not found for property");
            currentImage = null;
        }
        jDialog.dispose();
        this.repaint();
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
        if(currentImage != null){
            g2.drawImage(currentImage,500,300,this);
        }
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
                    JLabel loadingLabel = new JLabel("Fetching listings...");
                    loadingLabel.setBounds(500,100,100,100);
                    loadingLabel.setVisible(true);
                    // Loading window
                    JDialog jDialog = new JDialog();
                    jDialog.setLayout(new GridBagLayout());
                    jDialog.add(new JLabel("Fetching listings..."));
                    jDialog.setMinimumSize(new Dimension(150, 50));
                    jDialog.setResizable(false);
                    jDialog.setModal(false);
                    jDialog.setUndecorated(true);
                    jDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setVisible(true);
                    jDialog.revalidate();
                    jDialog.paintComponents(jDialog.getGraphics());
                    airbnbPopulator(country, maxPrice);
                    jDialog.dispose();
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
        else if(e.getSource() == websiteButton){
            try{
                if (selectedProperty != -1 && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI((String)currentProperties.get(selectedProperty).get("listing_url")));
                }

            }
            catch(URISyntaxException | IOException exception){
                JOptionPane.showMessageDialog(this, "Listing has an invalid website attached");
            }

        }
        else if (e.getSource() == mapButton)
        {
            //this.add()
        }
    }

}
