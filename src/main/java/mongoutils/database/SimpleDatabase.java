package mongoutils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleDatabase implements Database {

    private final MongoDatabase db;

    public SimpleDatabase(@NotNull MongoDatabase db) {
        this.db = db;
    }

    public SimpleDatabase(@NotNull MongoClient client, @NotNull String name) {
        this.db = client.getDatabase(name);
    }

    @Override
    public @NotNull MongoDatabase getMongoDatabase() {
        return db;
    }

    @Override
    public @NotNull MongoIterable<String> getCollections() {
        return db.listCollectionNames();
    }

    @Override
    public @Nullable MongoCollection<Document> getCollection(@NotNull String collectionName) {
        return db.getCollection(collectionName);
    }

    @Override
    public void createCollection(@NotNull String collectionName) {
        db.createCollection(collectionName);
    }

    @Override
    public void deleteCollection(@NotNull String collectionName) {
        MongoCollection<Document> mongoCollection = getCollection(collectionName);
    }
}
