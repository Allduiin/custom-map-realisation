package test.task.map;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class CustomMapImpl<V> implements CustomMap<V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int RESIZE_CONSTANT = 2;
    private int size;
    private Node<V>[] nodes;

    public CustomMapImpl() {
        nodes = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(Long key, V value) {
        if (size + 1 > DEFAULT_LOAD_FACTOR * nodes.length) {
            resize(nodes);
        }
        int index = getIndex(key);
        if (nodes[index] == null) {
            nodes[index] = new Node<>(key, value);
        } else {
            Node<V> curNode = nodes[index];
            while (curNode.next != null) {
                if (Objects.equals(curNode.key, key)) {
                    curNode.value = value;
                    return;
                }
                curNode = curNode.next;
            }
            if (Objects.equals(curNode.key, key)) {
                curNode.value = value;
                return;
            } else {
                curNode.next = new Node<>(key, value);
            }
        }
        size++;
    }

    public V getValue(Long key) {
        int index = getIndex(key);
        if (nodes[index] == null) {
            return null;
        }
        Node<V> curNode = nodes[index];
        while (curNode != null) {
            if (Objects.equals(curNode.key, key)) {
                return curNode.value;
            }
            curNode = curNode.next;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        if (nodes != null && size > 0) {
            size = 0;
            Arrays.fill(nodes, null);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public final void forEach(Consumer<? super V> cons) {
        if (cons == null)
            throw new NullPointerException();
        if (size > 0 && nodes != null) {
            for (Node<V> node : nodes) {
                for (; node != null; node = node.next)
                    cons.accept(node.value);
            }
        }
    }

    private void resize(Node<V>[] oldNodes) {
        nodes = new Node[nodes.length * RESIZE_CONSTANT];
        for (Node<V> node : oldNodes) {
            for (; node != null; node = node.next) {
                put(node.key, node.value);
            }
        }
    }

    private int getIndex(Long key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % nodes.length;
    }

    private static class Node<V> {
        private final Long key;
        private V value;
        private Node<V> next;

        private Node(Long key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
