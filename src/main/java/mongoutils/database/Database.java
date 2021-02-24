package mongoutils.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Database {

    @NotNull
    MongoDatabase getMongoDatabase();

    @NotNull
    MongoIterable<String> getCollections();

    @Nullable
    MongoCollection<? extends Document> getCollection(@NotNull String collectionName);

    void createCollection(@NotNull String collectionName);

    void deleteCollection(@NotNull String collectionName);

}
