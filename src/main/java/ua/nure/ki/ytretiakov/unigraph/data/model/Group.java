package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "GROUPS")
@Entity
public class Group extends IDEntity {

    @Column
    private String title;

    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "group")
    private List<Employee> students;

    public Group() {

    }

    public Group(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Employee> getStudents() {
        return students;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudents(List<Employee> students) {
        this.students = students;
    }
}
