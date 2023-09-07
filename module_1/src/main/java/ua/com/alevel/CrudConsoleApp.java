package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CrudConsoleApp {
    private static User[] users = new User[10];
    private static int userCount = 0;
    private static int userCounter = 1;
    private static Group[] groups = new Group[10];
    private static int groupCount = 0;
    private static int groupCounter = 1;
    private static final int DEFAULT_GROUP_ID = -1;

    private static void resizeUsersArray() {
        int newSize = users.length * 2;
        User[] newArray = new User[newSize];
        System.arraycopy(users, 0, newArray, 0, userCount);
        users = newArray;
    }

    private static void resizeGroupsArray() {
        int newSize = groups.length * 2;
        Group[] newArray = new Group[newSize];
        System.arraycopy(groups, 0, newArray, 0, groupCount);
        groups = newArray;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("1. Створити студента");
            System.out.println("2. Видалити студента");
            System.out.println("3. Створити групу");
            System.out.println("4. Видалити групу");
            System.out.println("5. Додати студента до групи");
            System.out.println("6. Вивести всіх студентів");
            System.out.println("7. Вивести всі групи");
            System.out.println("8. Вивести студентів у групу");
            System.out.println("9. Вийти");


            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неправильний формат даних. Будь ласка, введіть номер меню.");
                continue;
            }
            switch (choice) {
                case 1:
                    createUser(reader);
                    break;
                case 2:
                    deleteUser(reader);
                    break;
                case 3:
                    createGroup(reader);
                    break;
                case 4:
                    deleteGroup(reader);
                    break;
                case 5:
                    addUserToGroup(reader);
                    break;
                case 6:
                    listAllUsers();
                    break;
                case 7:
                    listAllGroups();
                    break;
                case 8:
                    listStudentsInGroup(reader);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void createUser(BufferedReader reader) throws IOException {
        if (userCount == users.length) {
            resizeUsersArray();
        }
        System.out.print("Введіть ім'я студента: ");
        String name = reader.readLine();
        users[userCount] = new User(userCounter, name, DEFAULT_GROUP_ID);
        userCount++;
        userCounter++;
        System.out.println("Студент створений.");
    }

    private static void deleteUser(BufferedReader reader) throws IOException {
        listAllUsers();
        System.out.print("Введіть ID студента, якого потрібно видалити: ");
        String userIdStr = reader.readLine();

        if (isNumeric(userIdStr)) {
            int userId = Integer.parseInt(userIdStr);
            boolean userExists = false;

            for (int i = 0; i < userCount; i++) {
                if (users[i].getId() == userId) {
                    userExists = true;
                    int groupId = users[i].getGroupId();

                    // Видаляємо студента
                    for (int j = i; j < userCount - 1; j++) {
                        users[j] = users[j + 1];
                    }
                    userCount--;

                    // Встановлюємо групу за замовчуванням для інших студентів, які були в тій же групі
                    if (groupId != DEFAULT_GROUP_ID) {
                        for (int j = 0; j < userCount; j++) {
                            if (users[j].getGroupId() == groupId) {
                                users[j].setGroupId(DEFAULT_GROUP_ID);
                            }
                        }
                    }
                    System.out.println("Студент видалений.");

                    // Оновлюємо лічильник ID студентів тільки, якщо студент був видалений в кінці списку
                    if (i == userCount) {
                        userCounter--;
                    }
                    break; // Вихід з циклу після успішного видалення
                }
            }

            if (!userExists) {
                System.out.println("Студент з таким ID не існує.");
            }
        } else {
            System.out.println("Невірний формат ID студента. Введіть числове значення.");
        }
    }

    private static void createGroup(BufferedReader reader) throws IOException {
        if (groupCount == groups.length) {
            resizeGroupsArray();
        }
        System.out.print("Введіть назву групи: ");
        String groupName = reader.readLine();
        groups[groupCount] = new Group(groupCounter, groupName);
        groupCounter++;
        groupCount++;
        System.out.println("Група створена.");
    }

    private static void deleteGroup(BufferedReader reader) throws IOException {
        listAllGroups();
        System.out.print("Введіть ID групи, яку потрібно видалити: ");
        String groupIdStr = reader.readLine();

        if (isNumeric(groupIdStr)) {
            int groupId = Integer.parseInt(groupIdStr);
            boolean groupExists = false;

            for (int i = 0; i < groupCount; i++) {
                if (groups[i].getId() == groupId) {
                    groupExists = true;
                    // Видаляємо групу
                    for (int j = i; j < groupCount - 1; j++) {
                        groups[j] = groups[j + 1];
                    }
                    groupCount--;

                    // Встановлюємо групу за замовчуванням для студентів, які були видалені з групи
                    for (int j = 0; j < userCount; j++) {
                        if (users[j].getGroupId() == groupId) {
                            users[j].setGroupId(DEFAULT_GROUP_ID);
                        }
                    }
                    System.out.println("Група видалена.");

                    // Оновлюємо лічильник ID групи тільки, якщо група була видалена в кінці списку
                    if (i == groupCount) {
                        groupCounter--;
                    }
                    break; // Вихід з циклу після успішного видалення
                }
            }

            if (!groupExists) {
                System.out.println("Група з таким ID не існує.");
            }
        } else {
            System.out.println("Невірний формат ID групи. Введіть числове значення.");
        }
    }

    private static void addUserToGroup(BufferedReader reader) throws IOException {
        if (userCount != 0 && groupCount != 0) {
            listAllUsers();
            System.out.print("Введіть ID студента: ");
            String userIdStr = reader.readLine();

            if (isNumeric(userIdStr)) {
                int userId = Integer.parseInt(userIdStr);
                listAllGroups();
                System.out.print("Введіть ID групи: ");
                String groupIdStr = reader.readLine();

                if (isNumeric(groupIdStr)) {
                    int groupId = Integer.parseInt(groupIdStr);

                    boolean userExists = false;
                    boolean groupExists = false;

                    for (int i = 0; i < userCount; i++) {
                        if (users[i].getId() == userId) {
                            userExists = true;
                            for (int j = 0; j < groupCount; j++) {
                                if (groups[j].getId() == groupId) {
                                    groupExists = true;
                                    users[i].setGroupId(groupId);
                                    System.out.println("Студента додано до групи.");
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    if (!userExists) {
                        System.out.println("Студент з таким ID не існує.");
                    }

                    if (!groupExists) {
                        System.out.println("Група з таким ID не існує.");
                    }
                } else {
                    System.out.println("Невірний формат ID групи. Введіть числове значення.");
                }
            } else {
                System.out.println("Невірний формат ID студента. Введіть числове значення.");
            }
        } else {
            System.out.println("Студентів або груп не створено.");
        }
    }

    private static void listAllUsers() {
        if (userCount != 0) {
            System.out.println("Список студентів:");
            for (int i = 0; i < userCount; i++) {
                User user = users[i];
                int userId = user.getId();
                String userName = user.getName();
                int groupId = user.getGroupId();

                String groupName = "Дефолтна групппа, то есть группа не назначена"; // За замовчуванням

                // Знаходимо назву групи за ID групи користувача
                for (int j = 0; j < groupCount; j++) {
                    if (groups[j].getId() == groupId) {
                        groupName = groups[j].getName();
                        break;
                    }
                }
                String idIfDefault = "default";
                if (groupId == -1) {
                    System.out.println("ID: " + userId + ", Ім'я: " + userName + ", ID групи: " + idIfDefault + ", Назва групи: " + groupName);
                } else {
                    System.out.println("ID: " + userId + ", Ім'я: " + userName + ", ID групи: " + groupId + ", Назва групи: " + groupName);

                }
            }
        } else {
            System.out.println("Студентов не создано");
        }

    }

    private static void listAllGroups() {
        System.out.println("Список груп:");
        for (int i = 0; i < groupCount; i++) {
            Group group = groups[i];
            System.out.println("ID: " + group.getId() + ", Назва: " + group.getName());
        }
    }

    private static void listStudentsInGroup(BufferedReader reader) throws IOException {
        if (userCount != 0 && groupCount != 0) {
            listAllGroups();
            System.out.print("Введіть ID групи для виводу студентів: ");
            String groupIdStr = reader.readLine();

            if (isNumeric(groupIdStr)) {
                int groupId = Integer.parseInt(groupIdStr);
                boolean groupExists = false;
                int studentsInGroup = 0;


                for (int i = 0; i < groupCount; i++) {
                    if (groups[i].getId() == groupId) {
                        groupExists = true;
                        System.out.println("Назва групи: " + groups[i].getName());
                        System.out.println("Група та її студенти:");

                        for (int j = 0; j < userCount; j++) {
                            if (users[j].getGroupId() == groupId) {
                                System.out.println("ID: " + users[j].getId() + ", Ім'я: " + users[j].getName());
                                studentsInGroup++;
                            }
                        }
                        break;
                    }
                }

                if (!groupExists) {
                    System.out.println("Група з таким ID не існує.");
                }
                if (studentsInGroup == 0) {
                    System.out.println("Cтудентов в группе нет");
                }
            } else {
                System.out.println("Невірний формат ID групи. Введіть числове значення.");
            }
        } else {
            System.out.println("Студент или группа не созданы");
        }
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return !str.isEmpty();
    }

}
