package test.task.map;

import java.util.function.Consumer;

public interface CustomMap<V> {
    public void put(Long key, V value);

    public V getValue(Long key);

    public int getSize();

    public void clear();

    public boolean isEmpty();

    public void forEach(Consumer<? super V> cons);
}
