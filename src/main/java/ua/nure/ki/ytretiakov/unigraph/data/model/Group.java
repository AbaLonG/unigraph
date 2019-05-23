package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Employee groupManager;

    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.EAGER)
    private List<Employee> students;

    @ManyToOne(targetEntity = Cathedra.class, cascade = CascadeType.ALL, optional = false)
    private Cathedra cathedra;

    public Group(final String title, final Employee groupManager, final Cathedra cathedra) {
        this.title = title;
        this.groupManager = groupManager;
        this.cathedra = cathedra;
    }

    public Group() {

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

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }
}
