package caches;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class L2Cache<K,V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final Map<K, Integer> frequencyMap;

    public L2Cache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            int minFreq = Integer.MAX_VALUE;
            K leastFrequentKey = null;
            for (Map.Entry<K, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() < minFreq) {
                    minFreq = entry.getValue();
                    leastFrequentKey = entry.getKey();
                }
            }
            cache.remove(leastFrequentKey);
            frequencyMap.remove(leastFrequentKey);
        }
        cache.put(key, value);
        frequencyMap.put(key, 1);
    }

    public boolean contains(K key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
        frequencyMap.clear();
    }
}
