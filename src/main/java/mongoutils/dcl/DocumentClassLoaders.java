package mongoutils.dcl;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface DocumentClassLoaders {

    @NotNull
    Map<Class<?>, DocumentClassLoader<?, Document>> getLoaders();

    void addLoader(DocumentClassLoader<?, Document> loader);

    <Loader> DocumentClassLoader<Loader, Document> getLoader(Class<Loader> loadType);

}
