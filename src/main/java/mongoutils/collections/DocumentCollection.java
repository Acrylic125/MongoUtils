package mongoutils.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import mongoutils.database.Database;
import mongoutils.query.DocumentQuery;
import org.jetbrains.annotations.NotNull;

public interface DocumentCollection<TDocument> {

    @NotNull
    Database getDatabase();

    @NotNull
    MongoCollection<TDocument> getCollection();

    DocumentQuery<TDocument> queryDocument();

    void insertOne(@NotNull TDocument document);


}
