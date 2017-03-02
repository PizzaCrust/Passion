package passion.collect;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Abstract class for immutable maps.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public abstract class ImmutableMap<K, V> implements Map<K, V> {

    @Deprecated
    @Override
    public V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Computation is not allowed in immutable maps!");
    }

    @Deprecated
    @Override
    public V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction) {
        throw new UnsupportedOperationException("Computation is not allowed in immutable maps!");
    }

    @Deprecated
    @Override
    public V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V>
            remappingFunction) {
        throw new UnsupportedOperationException("Computation is not allowed in immutable map!");
    }

    @Deprecated
    @Override
    public V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Merging is not allowed in a immutable map!");
    }

    public static <K, V> ImmutableMap<K, V> constructFrom(Map<K, V> map) {
        return new LazyImmutableMap<>(map);
    }

    @Deprecated
    @Override
    public V put(K k, V v) {
        return get(k) != null ? get(k) : v;
    }

    @Override
    @Deprecated
    public void putAll(Map<? extends K,? extends V> map) {

    }

    @Deprecated
    @Override
    public V putIfAbsent(K key, V value) {
        return put(key, value);
    }

    @Override
    @Deprecated
    public V remove(Object o) {
        return get(o) != null ? get(o) : (V) o;
    }

    @Override
    @Deprecated
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    @Deprecated
    public V replace(K key, V value) {
        return get(key) != null ? get(key) : value;
    }

    @Deprecated
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    @Deprecated
    public void replaceAll(BiFunction<? super K,? super V,? extends V> function) {

    }

    @Deprecated
    @Override
    public void clear() {

    }


}
