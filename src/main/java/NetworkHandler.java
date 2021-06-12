import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NetworkHandler {
    public static MongoClient mongoClient;
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
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
        System.out.println("d");

    }
}
