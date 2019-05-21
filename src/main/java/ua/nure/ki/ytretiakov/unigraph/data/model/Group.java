package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUPS")
public class Group extends IDEntity {

    @Column
    private String title;

    @ManyToOne(targetEntity = GroupManager.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "employee_id_fk"))
    private Employee groupManager;

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

    public Employee getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(Employee groupManager) {
        this.groupManager = groupManager;
    }
}
