package ua.com.alevel;

public class Dictionary<K, V> {
    private Object[] keys;
    private Object[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Dictionary() {
        keys = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    public boolean put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return true;
            }
        }
        ensureCapacity(size + 1);
        keys[size] = key;
        values[size] = value;
        size++;
        return true;
    }

    public boolean remove(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                for (int j = i; j < size - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean putAll(Dictionary<K, V> otherDictionary) {
        boolean modified = false;
        for (int i = 0; i < otherDictionary.size(); i++) {
            K key = (K) otherDictionary.keys[i];
            V value = (V) otherDictionary.values[i];
            if (!containsKey(key) || !get(key).equals(value)) {
                put(key, value);
                modified = true;
            }
        }
        return modified;
    }

    public boolean clear() {
        keys = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
        return true;
    }

    public Object[] keySet() {
        Object[] keyArray = new Object[size];
        System.arraycopy(keys, 0, keyArray, 0, size);
        return keyArray;
    }

    public Object[] values() {
        Object[] valueArray = new Object[size];
        System.arraycopy(values, 0, valueArray, 0, size);
        return valueArray;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > keys.length) {
            int newCapacity = Math.max(capacity, keys.length * 2);
            keys = resizeArray(keys, newCapacity);
            values = resizeArray(values, newCapacity);
        }
    }

    private Object[] resizeArray(Object[] array, int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }

    public static void main(String[] args) {
        Dictionary<String, Integer> dict = new Dictionary<>();
        dict.put("one", 1);
        dict.put("two", 2);
        dict.put("three", 3);

        System.out.println("Size: " + dict.size());
        System.out.println("Contains key 'two': " + dict.containsKey("two"));
        System.out.println("Contains value 4: " + dict.containsValue(4));

        System.out.println("Get 'two': " + dict.get("two"));

        System.out.println("Removing 'two': " + dict.remove("two"));
        System.out.println("Contains key 'two' after removal: " + dict.containsKey("two"));

        Dictionary<String, Integer> otherDict = new Dictionary<>();
        otherDict.put("three", 33);
        otherDict.put("four", 4);

        System.out.println("PutAll result: " + dict.putAll(otherDict));
        System.out.println("After putAll:");
        System.out.println("Size: " + dict.size());
        System.out.println("Contains key 'three': " + dict.containsKey("three"));
        System.out.println("Value for key 'three': " + dict.get("three"));

        dict.clear();
        System.out.println("Size after clear: " + dict.size());
    }
}