package ua.com.alevel.db;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Human;

public class GroupDb {
    private Group[] groups;
    private int groupCount;

    public GroupDb(int initialSize) {
        groups = new Group[initialSize];
        groupCount = 0;
    }

    public void createGroup(int groupId, String name) {
        if (groupCount == groups.length) {
            // Если массив групп заполнен, увеличиваем его размер
            Group[] newGroups = new Group[groups.length * 2];
            System.arraycopy(groups, 0, newGroups, 0, groups.length);
            groups = newGroups;
        }
        groups[groupCount++] = new Group(groupId, name);
    }

    public void deleteGroup(int groupId, HumanDb humanDb) {
        for (int i = 0; i < groupCount; i++) {
            if (groups[i].getGroupId() == groupId) {
                // Сначала удаляем студентов из этой группы
                for (Human human : humanDb.findAll()) {
                    if (human.getGroupId().equals(String.valueOf(groupId))) {
                        human.setGroupId(null);
                    }
                }
                groups[i] = null; // Удаляем группу из массива
                groupCount--;

                // Сжимаем массив, чтобы не оставалось пустых ячеек
                for (int j = i; j < groupCount; j++) {
                    groups[j] = groups[j + 1];
                }
                groups[groupCount] = null; // Очищаем последний элемент
                break;
            }
        }
    }

    public void printGroups() {
        System.out.println("Список групп:");
        for (int i = 0; i < groupCount; i++) {
            Group group = groups[i];
            System.out.println("Группа ID: " + group.getGroupId() + ", Название: " + group.getName());
        }
    }

    public void printStudentsInGroup(int groupId, HumanDb humanDb) {
        System.out.println("Студенты в группе " + groupId + ":");
        for (Human human : humanDb.findAll()) {
            if (human != null && human.getGroupId() != null && human.getGroupId().equals(String.valueOf(groupId))) {
                if (!human.isDeleted()) {
                    System.out.println("ID: " + human.getId() + ", Имя: " + human.getFirstName()
                            + ", Фамилия: " + human.getLastName() + ", Возраст: " + human.getAge());
                } else {
                    System.out.println("Student is deleted: ID - " + human.getId());
                }
            }
        }
    }
    public void addStudentToGroup(int studentId, int groupId, HumanDb humanDb) {
        Human[] students = humanDb.findAll();
        for (int i = 0; i < students.length; i++) {
            Human student = students[i];
            if (student != null && student.getId().equals(String.valueOf(studentId))) {
                student.setGroupId(String.valueOf(groupId));
                break;
            }
        }
    }
}
