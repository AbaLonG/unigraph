package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;

@Entity
@Table(name = "CATHEDRAS")
public class Cathedra extends IDEntity {

    @Column
    private String title;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Employee cathedraManager;

    @ManyToOne(targetEntity = Faculty.class, cascade = CascadeType.ALL)
    private Faculty faculty;

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

    @Override
    public String toString() {
        return "Cathedra{" +
                "id=" + id +
                '}';
    }
}
