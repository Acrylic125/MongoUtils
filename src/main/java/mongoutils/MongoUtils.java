package mongoutils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;

public class MongoUtils {

    private final MongoClient mongoClient;

    public MongoUtils(@NotNull MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoUtils connectByURL(@NotNull String url) {
        return new MongoUtils(MongoClients.create(url));
    }

    public static void main(String[] args) {

    }

}
