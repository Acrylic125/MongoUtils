package mongoutils.dcl;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SimpleDocumentClassLoaders implements DocumentClassLoaders<Document> {

    private final Map<Class<?>, DocumentClassLoader<?, Document>> loaders;

    public SimpleDocumentClassLoaders() {
        this(new HashMap<>());
    }

    public SimpleDocumentClassLoaders(@NotNull Map<Class<?>, DocumentClassLoader<?, Document>> loaders) {
        this.loaders = loaders;
    }

    @Override
    public @NotNull Map<Class<?>, DocumentClassLoader<?, Document>> getLoaders() {
        return loaders;
    }

    @Override
    public void addLoader(DocumentClassLoader<?, Document> loader) {
        loaders.put(loader.getClass(), loader);
    }
}
