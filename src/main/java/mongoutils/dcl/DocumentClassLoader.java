package mongoutils.dcl;

import mongoutils.collections.DocumentCollection;
import org.jetbrains.annotations.NotNull;

public interface DocumentClassLoader<T, TDocument> {

    @NotNull
    DocumentCollection<TDocument> getRoot();

    void save(@NotNull T entity);

    void load(@NotNull TDocument tResult);

}
