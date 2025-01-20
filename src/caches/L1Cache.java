package caches;
import java.util.LinkedHashMap;
import java.util.Map;

public class L1Cache<K,V> {
    private final int capacity;
    private final Map<K, V> cache;

    public L1Cache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity,0.75f,true);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            cache.remove(cache.keySet().iterator().next());
        }
        cache.put(key, value);
    }

    public boolean contains(K key) {
        return cache.containsKey(key);
    }
    public void clear() {
        cache.clear();
    }

}
