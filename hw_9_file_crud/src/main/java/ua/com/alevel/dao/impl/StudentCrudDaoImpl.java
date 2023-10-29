package ua.com.alevel.dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import ua.com.alevel.dao.CrudDao;
import ua.com.alevel.dao.StudentCrudDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.GroupDbUtil;
import ua.com.alevel.util.StudentDbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StudentCrudDaoImpl implements StudentCrudDao {

    private List<Student> students = new ArrayList<>();
    @Override
    public void create(Student student) {
        readCsv();
        student.setId(StudentDbUtil.generateId(students));
        students.add(student);
        writeCsv();
    }

    @Override
    public void update(long id, Student student) {
        readCsv();
        for (Student st : students) {
            if (st.getId() == id){
                st.setGroupId(student.getGroupId());
            }
        }
        writeCsv();
    }

    @Override
    public void delete(Long id) {
        readCsv();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove(); // Remove the element using the iterator
            }
        }
        writeCsv();
    }
    @Override
    public boolean existsById(Long id) {
        readCsv();
        return students.stream().anyMatch(student -> student.getId() == id);
    }

    @Override
    public Collection findAll() {
        readCsv();
        return students;
    }

    @Override
    public Student findById(long id) {
        readCsv();
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private void readCsv() {
        try(CSVReader csvReader = new CSVReader(new FileReader("students.csv"), ';')) {

            List<String[]> list = csvReader.readAll();
            students = new ArrayList<>();
            for (String[] strings : list) {
                Student student = new Student();
                student.setId(Long.valueOf(strings[0]));
                student.setName(strings[1]);
                student.setLast_name(strings[2]);
                student.setAge(Integer.parseInt(strings[3]));
                student.setGroupId(Long.valueOf(strings[4]));
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeCsv() {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("students.csv"), ';')) {
            List<String[]> list = new ArrayList<>();
            for (Student student : students) {
                String[] strings = new String[] {
                        String.valueOf(student.getId()),
                        student.getName(),
                        student.getLast_name(),
                        String.valueOf(student.getAge()),
                        String.valueOf(student.getGroupId())
                };
                list.add(strings);
            }
            csvWriter.writeAll(list);
            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
