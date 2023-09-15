package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseStringLibrary {

    public static String reverse(String src) {
        char[] chars = src.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return "<- " + new String(chars);
    }

    public static String reverse(String src, String dest) {
        if (!src.contains(dest)) {
            throw new IllegalArgumentException("Source string must contain the destination substring.");
        }

        int startIndex = src.indexOf(dest);
        int endIndex = startIndex + dest.length();

        char[] chars = src.toCharArray();
        char[] substringChars = dest.toCharArray();
        int left = startIndex;
        int right = endIndex - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = substringChars[right - startIndex];
            chars[right] = substringChars[left - startIndex];
            left++;
            right--;
        }

        return new String(chars);
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= src.length() || firstIndex > lastIndex) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        char[] chars = src.toCharArray();
        int left = firstIndex;
        int right = lastIndex;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            System.out.println("Выберите операцию:");
            System.out.println("1. Обычный реверс строки");
            System.out.println("2. Реверс по подстроке");
            System.out.println("3. Реверс по индексам");
            System.out.println("4. Выход");

            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный выбор операции.");
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите строку:");
                    String input1 = reader.readLine();
                    String result1 = reverse(input1);
                    System.out.println("Результат: " + result1);
                    break;

                case 2:
                    System.out.println("Введите строку:");
                    String input2 = reader.readLine();
                    System.out.println("Введите подстроку:");
                    String substring = reader.readLine();
                    String result2 = reverse(input2, substring);
                    System.out.println("Результат: " + result2);
                    break;

                case 3:
                    System.out.println("Введите строку:");
                    String input3 = reader.readLine();
                    System.out.println("Введите первый индекс:");
                    int firstIndex = Integer.parseInt(reader.readLine());
                    System.out.println("Введите последний индекс:");
                    int lastIndex = Integer.parseInt(reader.readLine());
                    String result3 = reverse(input3, firstIndex, lastIndex);
                    System.out.println("Результат: " + result3);
                    break;

                case 4:
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Неверный выбор операции.");
            }
        } while (choice != 4);

        reader.close();
    }
}
