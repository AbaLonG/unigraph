package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FACULTIES")
public class Faculty {

    @Id
    @Column(unique = true, nullable = false)
    private String title;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL, optional = false)
    private Employee facultyManager;

    @OneToMany(targetEntity = Cathedra.class, cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Cathedra> cathedras;

    public Faculty(final String title, final Employee facultyManager) {
        this.title = title;
        this.facultyManager = facultyManager;
    }

    public Faculty() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Employee getFacultyManager() {
        return facultyManager;
    }

    public void setFacultyManager(final Employee facultyManager) {
        this.facultyManager = facultyManager;
    }

    public List<Cathedra> getCathedras() {
        return cathedras;
    }

    public void setCathedras(final List<Cathedra> cathedras) {
        this.cathedras = cathedras;
    }
}
