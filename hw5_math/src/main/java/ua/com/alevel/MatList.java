package ua.com.alevel;
import java.util.Arrays;

public class MatList<E extends Number & Comparable<E>> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MatList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public MatList(E[]... numbers) {
        this();
        for (E[] arr : numbers) {
            add(arr);
        }
    }

    public MatList(MatList<E>... matLists) {
        this();
        for (MatList<E> matList : matLists) {
            addArray((E[]) matList.toArray());
        }
    }

    public void addArray(E[] n) {
        ensureCapacity(size + n.length);
        System.arraycopy(n, 0, elements, size, n.length);
        size += n.length;
    }

    public void add(E... n) {
        ensureCapacity(size + n.length);
        for (E num : n) {
            elements[size] = num;
            size++;
        }
    }

    public void join(MatList... ml) {
        for (MatList matList : ml) {
            for (int i = 0; i < matList.size; i++) {
                add((E) matList.get(i)); // Cast to E
            }
        }
    }

    public void intersection(MatList... ml) {
        if (ml.length == 0) {
            clear();
            return;
        }

        for (int i = 0; i < size; i++) {
            E num = (E) elements[i];
            boolean isInAll = true;
            for (MatList matList : ml) {
                if (!matList.contains(num)) {
                    isInAll = false;
                    break;
                }
            }
            if (!isInAll) {
                remove(num);
                i--;
            }
        }
    }

    public void sortDesc() {
        Arrays.sort(elements, 0, size, (a, b) -> -((E) a).compareTo((E) b));
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex > lastIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }
        Arrays.sort(elements, firstIndex, lastIndex + 1, (a, b) -> -((E) a).compareTo((E) b));
    }

    public void sortDesc(E value) {
        if (!contains(value)) {
            return;
        }
        int index = indexOf(value);
        sortDesc(index, size - 1);
    }

    public void sortAsc() {
        Arrays.sort(elements, 0, size, (a, b) -> ((E) a).compareTo((E) b));
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex > lastIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }
        Arrays.sort(elements, firstIndex, lastIndex + 1, (a, b) -> ((E) a).compareTo((E) b));
    }

    public void sortAsc(E value) {
        if (!contains(value)) {
            return;
        }
        int index = indexOf(value);
        sortAsc(index, size - 1);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (E) elements[index];
    }

    public Number getMax() {
        if (size == 0) {
            throw new IllegalStateException("MatList is empty");
        }
        sortDesc();
        return (E) elements[0];
    }

    public Number getMin() {
        if (size == 0) {
            throw new IllegalStateException("MatList is empty");
        }
        sortAsc();
        return (E) elements[0];
    }

    public Number getAverage() {
        if (size == 0) {
            throw new IllegalStateException("MatList is empty");
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += ((E) elements[i]).doubleValue();
        }
        return sum / size;
    }

    public Number getMedian() {
        if (size == 0) {
            throw new IllegalStateException("MatList is empty");
        }
        sortAsc();
        if (size % 2 == 0) {
            int middle1 = (size - 1) / 2;
            int middle2 = size / 2;
            return (double) (((E) elements[middle1]).doubleValue() + ((E) elements[middle2]).doubleValue()) / 2;
        } else {
            int middle = size / 2;
            return ((E) elements[middle]).doubleValue();
        }
    }

    public Number[] toArray() {
        Number[] result = new Number[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex > lastIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }
        int length = lastIndex - firstIndex + 1;
        Number[] result = new Number[length];
        System.arraycopy(elements, firstIndex, result, 0, length);
        return result;
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex > lastIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }

        MatList<E> result = new MatList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            result.add(get(i));
        }

        // Remove the elements from the original list
        removeRange(firstIndex, lastIndex);

        return result;
    }

    public void clear() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void clear(E[] numbers) {
        for (E num : numbers) {
            remove(num);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    private void ensureCapacity(int additionalCapacity) {
        while (size + additionalCapacity > elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                // Shift elements to fill the gap
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null;
                size--;
                return;
            }
        }
    }

    private void removeRange(int fromIndex, int toIndex) {
        int length = toIndex - fromIndex + 1;
        // Shift elements to fill the gap
        System.arraycopy(elements, toIndex + 1, elements, fromIndex, size - toIndex - 1);
        for (int i = size - length; i < size; i++) {
            elements[i] = null;
        }
        size -= length;
    }

    private boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }
}
