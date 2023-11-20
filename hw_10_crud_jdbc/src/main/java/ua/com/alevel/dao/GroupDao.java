package ua.com.alevel.dao;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public interface GroupDao extends CrudDao<Group> {
    void addStudentsToGroup(Long stud_id, Long dep_id);
}
