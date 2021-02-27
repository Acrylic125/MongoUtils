package mongoutils.datastore;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mongoutils.MongoUtils;
import mongoutils.query.DocumentQuery;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

public interface Datastore {

    @NotNull
    MongoDatabase getMongoDatabase();

    @NotNull
    <T> MongoCollection<T> getMongoCollection(@NotNull String collectionName, @NotNull Class<T> clazz);

    @NotNull
    MongoCollection<Document> getMongoCollection(@NotNull String collectionName);

    @NotNull
    <T> MongoCollection<T> getMongoCollection(@NotNull Class<T> clazz);

    <T> DocumentQuery<T> query(@NotNull String collectionName, @NotNull Class<T> clazz);

    DocumentQuery<Document> query(@NotNull String collectionName);

    <T> DocumentQuery<T> query(@NotNull Class<T> clazz);

    <T> void save(@NotNull String collectionName, @NotNull T object);

    <T> void save(@NotNull T object);

    <T> void saveAll(@NotNull String collectionName, @NotNull T... objects);

    <T> void saveAll(@NotNull T... objects);

    <T> void saveAll(@NotNull String collectionName, @NotNull Collection<T> objects);

    <T> void saveAll(@NotNull Collection<T> objects);

    <T> void saveAll(@NotNull String collectionName, @NotNull Iterator<T> objects);

    <T> void saveAll(@NotNull Iterator<T> objects);

}
