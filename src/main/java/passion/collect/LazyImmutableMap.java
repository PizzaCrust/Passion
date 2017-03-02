package passion.collect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Lazy use of {@link ImmutableMap}, but still very effective.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class LazyImmutableMap<K, V> extends ImmutableMap<K, V> {

    private final Map<K, V> map;

    public LazyImmutableMap(Map<K, V> map) {
        this.map = map;
    }

    public static void main(String... args) {
        Map<String, String> oriMap = new HashMap<>();
        oriMap.put("meow", "lol");
        ImmutableMap<String, String> map = new LazyImmutableMap<>(oriMap);
        map.put("meow", "meow");
        System.out.println(oriMap.get("meow") + " : " + map.get("meow"));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

}
