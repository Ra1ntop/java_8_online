import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Пример использования MatList с Integer
        MatList<Integer> list1 = new MatList<>(new Integer[]{1, 2, 3});
        MatList<Integer> list2 = new MatList<>(new Integer[]{4, 5, 6});
        MatList<Integer> list3 = new MatList<>(new Integer[]{7, 8, 9});

        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println("list3 = " + list3);
        // Добавление элементов
        System.out.println("Adding elements..");

        list1.add(6);
        list2.add(7, 8);
        list3.add(9);

        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println("list3 = " + list3);

        // Объединение MatList
        list1.join(list2, list3);
        System.out.println("Joined List1 with List2 and List3: " + list1);

        // Пересечение MatList
        list1.intersection(list2, list3);
        System.out.println("Intersection of List1, List2, and List3: " + list1);

        // Сортировка в убывающем порядке
        list1.sortDesc();
        System.out.println("Sorted in descending order: " + list1);

        // Сортировка в убывающем порядке с указанием диапазона
        list1.sortDesc(1, 3);
        System.out.println("Sorted in descending order (range 1-3): " + list1);

        // Сортировка в убывающем порядке, начиная с определенного элемента
        list1.sortDesc(3);
        System.out.println("Sorted in descending order (starting from 3): " + list1);

        // Получение максимального, минимального, среднего и медианного значений
        Number max = list1.getMax();
        Number min = list1.getMin();
        Number avg = list1.getAverage();
        Number median = list1.getMedian();

        System.out.println("Max value: " + max);
        System.out.println("Min value: " + min);
        System.out.println("Average value: " + avg);
        System.out.println("Median value: " + median);

        // Преобразование в массив
        Number[] array = list1.toArray();
        System.out.println("Array representation: " + Arrays.toString(array));

        // Вырезание элементов из списка
        MatList<Integer> cutResult = list1.cut(1, 3);
        System.out.println("Cut result from List1: " + cutResult);
        System.out.println("Modified List1 after cut: " + list1);

        // Очистка списка
        list1.clear();
        System.out.println("List1 after clear: " + list1);
    }
}