package mongoutils.dcl;

import mongoutils.collections.DocumentCollection;
import org.jetbrains.annotations.NotNull;

public interface DocumentClassLoader<T, TDocument> {

    void save(@NotNull DocumentCollection<TDocument> collection, @NotNull T entity);

    void load(@NotNull DocumentCollection<TDocument> collection, @NotNull TDocument tResult);

}
