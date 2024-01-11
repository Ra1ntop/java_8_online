package ua.com.alevel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "age")
    Integer age;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private Set<Group> groups;

    @PreRemove
    private void removeGroupAssociations() {
        for (Group group : this.groups) {
            group.getStudents().remove(this);
        }
    }

    public Student() {
        groups = new HashSet<>();
    }
}