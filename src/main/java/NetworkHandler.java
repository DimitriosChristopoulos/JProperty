import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class NetworkHandler {
    public static MongoClient mongoClient;
    public static MongoCollection<Document> listings;
    public static void init(){
        String password = "";
        try{
            File passwordFile = new File("MongoDBPass.txt");
            Scanner fileReader = new Scanner(passwordFile);
            password = fileReader.nextLine();
        }
        catch(FileNotFoundException exception){
            exception.printStackTrace();
            System.out.println("Password file not found!");
            System.exit(1);
        }
        mongoClient = MongoClients.create(
                "mongodb+srv://Armaan:" + password + "@cluster0.dnqxb.mongodb.net/sample_airbnb?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("sample_airbnb");
        listings = database.getCollection("listingsAndReviews");
    }
    public static ArrayList<Document> getListings(int numberOfListings){
        ArrayList<Document> listingsArray = new ArrayList<>();
        for(Document listing: listings.aggregate(Collections.singletonList(Aggregates.sample(numberOfListings)))){
            listingsArray.add(listing);
        }
        return listingsArray;
    }
}
