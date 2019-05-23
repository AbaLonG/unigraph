package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATHEDRAS")
public class Cathedra {

    @Id
    @Column(unique = true, nullable = false)
    private String title;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL, optional = false)
    private Employee cathedraManager;

    @ManyToOne(targetEntity = Faculty.class, cascade = CascadeType.ALL, optional = false)
    private Faculty faculty;

    @OneToMany(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cathedra", orphanRemoval = true)
    private List<Group> groups;

    public Cathedra() {

    }

    public Cathedra(final String title) {
        this.title = title;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getCathedraManager() {
        return cathedraManager;
    }

    public void setCathedraManager(Employee cathedraManager) {
        this.cathedraManager = cathedraManager;
    }
}
