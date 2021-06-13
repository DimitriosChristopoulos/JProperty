import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HeatMapPanel extends JPanel {
    private ArrayList<Double> lattitudes = new ArrayList<Double>();
    private ArrayList<Double> longitudes = new ArrayList<Double>();

    public HeatMapPanel()
    {
        addCords();
        setVisible(true);
    }

    public void addCords(){
        try{
            File cords = new File("latsAndLongs.txt");
            Scanner fileReader = new Scanner(cords);
            while(fileReader.hasNextLine()){
                String newLine = fileReader.nextLine();
                String[] newCoords = newLine.split(",");
                Double newLat = Double.parseDouble(newCoords[0]);
                lattitudes.add(newLat);
                Double newLong = Double.parseDouble(newCoords[1]);
                longitudes.add(newLong);
            }
        }
        catch(FileNotFoundException exception){
            System.out.println("cant find it");
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args){
        HeatMapPanel myPanel = new HeatMapPanel();
    }
}
