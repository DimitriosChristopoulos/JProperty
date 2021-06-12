import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;

public class NetworkHandler {
    public static MongoClient mongoClient;
    public static void init(){
        mongoClient = MongoClients.create(
                "mongodb+srv://Armaan:ODF2KpeTMIUyHNQO@cluster0.dnqxb.mongodb.net/sample_airbnb?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("sample_airbnb");
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
        System.out.println("d");

    }
}
