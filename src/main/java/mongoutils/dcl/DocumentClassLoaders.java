package mongoutils.dcl;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface DocumentClassLoaders<TDocument> {

    @NotNull
    Map<Class<?>, DocumentClassLoader<?, TDocument>> getLoaders();

    void addLoader(DocumentClassLoader<?, TDocument> loader);



}
