package ua.com.alevel.dao.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import ua.com.alevel.dao.GroupCrudDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.GroupDbUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GroupCrudDaoImpl implements GroupCrudDao {
    private List<Group> groups = new ArrayList<>();
    @Override
    public void create(Group group) {
        readCsv();
        group.setId(GroupDbUtil.generateId(groups));
        groups.add(group);
        writeCsv();
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Long id) {
        readCsv();
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group.getId() == id) {
                iterator.remove();
            }
        }
        writeCsv();
    }

    @Override
    public boolean existsById(Long id) {
        readCsv();
        return groups.stream().anyMatch(group -> group.getId() == id);
    }

    @Override
    public Collection findAll() {
        readCsv();
        return groups;
    }

    private void readCsv() {
        try(CSVReader csvReader = new CSVReader(new FileReader("groups.csv"), ';')) {
            List<String[]> list = csvReader.readAll();
            groups = new ArrayList<>();
            for (String[] strings : list) {
                Group group = new Group();
                group.setId(Long.valueOf(strings[0]));
                group.setName(strings[1]);
                groups.add(group);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeCsv() {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("groups.csv"), ';')) {
            List<String[]> list = new ArrayList<>();
            for (Group group : groups) {
                String[] strings = new String[] {
                        String.valueOf(group.getId()),
                        group.getName(),
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
