package mongoutils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import mongoutils.MongoUtils;
import mongoutils.dcl.DocumentClassLoaders;
import mongoutils.dcl.SimpleDocumentClassLoaders;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleDatabase implements Database {

    private final MongoDatabase db;
    private final MongoUtils mongoUtils;

    public SimpleDatabase(@NotNull MongoUtils mongoUtils, @NotNull MongoDatabase db) {
        this.mongoUtils = mongoUtils;
        this.db = db;
    }

    public SimpleDatabase(@NotNull MongoUtils mongoUtils, @NotNull MongoClient client, @NotNull String name) {
        this.mongoUtils = mongoUtils;
        this.db = client.getDatabase(name);
    }

    @Override
    public DocumentClassLoaders getClassLoaders() {
        return mongoUtils.getClassLoaders();
    }

    @Override
    public @NotNull MongoDatabase getMongoDatabase() {
        return db;
    }

    @Override
    public @NotNull MongoIterable<String> getMongoCollections() {
        return db.listCollectionNames();
    }

    @Override
    public @Nullable MongoCollection<Document> getMongoCollection(@NotNull String collectionName) {
        return db.getCollection(collectionName);
    }

    @Override
    public void createMongoCollection(@NotNull String collectionName) {
        db.createCollection(collectionName);
    }

    @Override
    public void deleteMongoCollection(@NotNull String collectionName) {
        MongoCollection<Document> mongoCollection = getMongoCollection(collectionName);
        if (mongoCollection != null)
            mongoCollection.drop();
    }
}
