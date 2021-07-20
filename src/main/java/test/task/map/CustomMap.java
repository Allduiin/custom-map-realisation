package test.task.map;

import java.util.function.Consumer;

public interface CustomMap<V> {
    void put(Long key, V value);

    V getValue(Long key);

    int getSize();

    void clear();

    boolean isEmpty();

    void forEach(Consumer<? super V> cons);
}
