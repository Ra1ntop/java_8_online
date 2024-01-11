package ua.com.alevel.service;


import ua.com.alevel.entity.Group;

public interface GroupCrudService extends CrudService<Group> {

    void addStudentToGroup(Long groupId, Long studentId);

    void deleteStudentFromGroup(Long groupId, Long studentId);
}