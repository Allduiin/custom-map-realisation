package test.task.map;

import java.util.function.Consumer;

public class CustomMapImpl<V> implements CustomMap<V> {

    public CustomMapImpl(){

    }

    public void put(Long key, V value) {
    }

    public V getValue(Long key) {
        return null;
    }

    public int getSize() {
        return 0;
    }

    public void clear() {

    }

    public boolean isEmpty() {
        return false;
    }

    public final void forEach(Consumer<? super V> cons) {

    }
}