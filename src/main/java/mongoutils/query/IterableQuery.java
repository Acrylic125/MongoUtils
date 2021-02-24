package mongoutils.query;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class IterableQuery<T> implements Query<T> {

    private final Iterator<T> iterator;

    public IterableQuery(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public @Nullable T queryFirst() {
        while (iterator.hasNext()) {
            T obj = iterator.next();
            return obj;
        }
        return null;
    }

    @Override
    public void queryAndIterate(@NotNull Consumer<T> action) {

    }

    @Override
    public Collection<T> queryAll() {
        return null;
    }

    @Override
    public Query<T> filter(@NotNull String condition, Object matchWith) {
        return null;
    }

    @Override
    public Query<T> filter(@NotNull Predicate<T> filter) {
        return null;
    }

    @Override
    public @Nullable Collection<Predicate<T>> getFilters() {
        return null;
    }

    @Override
    public void startQuerying() {

    }
}
