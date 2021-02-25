package mongoutils.dcl;

import mongoutils.collections.DocumentCollection;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

public class SimpleDocumentClassLoader<T> implements DocumentClassLoader<T, Document> {

    @Override
    public void save(@NotNull DocumentCollection<Document> collection, @NotNull T entity) {

    }

    @Override
    public void load(@NotNull DocumentCollection<Document> collection, @NotNull Document tResult) {

    }
}
