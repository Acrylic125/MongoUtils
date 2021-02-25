package mongoutils.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import mongoutils.dcl.DocumentClassLoader;
import mongoutils.dcl.DocumentClassLoaders;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Database {

    DocumentClassLoaders getClassLoaders();

    @NotNull
    MongoDatabase getMongoDatabase();

    @NotNull
    MongoIterable<String> getMongoCollections();

    @Nullable
    MongoCollection<? extends Document> getMongoCollection(@NotNull String collectionName);

    void createMongoCollection(@NotNull String collectionName);

    void deleteMongoCollection(@NotNull String collectionName);

}
