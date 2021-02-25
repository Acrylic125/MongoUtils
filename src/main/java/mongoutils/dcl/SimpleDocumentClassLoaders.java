package mongoutils.dcl;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SimpleDocumentClassLoaders implements DocumentClassLoaders {

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

    @Override
    @SuppressWarnings("unchecked")
    public <Loader> DocumentClassLoader<Loader, Document> getLoader(Class<Loader> loadType) {
        DocumentClassLoader<?, Document> loader = loaders.get(loadType);
        try {
            return (DocumentClassLoader<Loader, Document>) loader;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
