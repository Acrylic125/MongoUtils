package mongoutils.query;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Query<T> {

    @Nullable
    T queryFirst();

    void queryAndIterate(@NotNull Consumer<T> action);

    Collection<T> queryAll();

    /**
     *
     *
     * @param condition The condition.
     * @param matchWith The value to compare with.
     * @return this.
     */
    Query<T> filter(@NotNull String condition, Object matchWith);

    Query<T> filter(@NotNull Predicate<T> filter);

    @Nullable
    Collection<Predicate<T>> getFilters();

    void startQuerying();

    default boolean matchesWithFilters(T object) {
        
        for (Predicate<T> filter : getFilters()) {

        }
    }

}
